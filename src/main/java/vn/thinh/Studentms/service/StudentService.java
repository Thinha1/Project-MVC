package vn.thinh.Studentms.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.thinh.Studentms.model.DTO.ScoreDTO;
import vn.thinh.Studentms.model.DTO.StudentDTO;
import vn.thinh.Studentms.model.entity.*;
import vn.thinh.Studentms.model.repository.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private ClassRepository classRepository;
    private ScoreRepository scoreRepository;
    private SubjectRepository subjectRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentService(RoleRepository roleRepository, UserRepository userRepository, StudentRepository studentRepository, CourseRepository courseRepository, ClassRepository classRepository, ScoreRepository scoreRepository, SubjectRepository subjectRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.classRepository = classRepository;
        this.scoreRepository = scoreRepository;
        this.subjectRepository = subjectRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void createStudent(StudentDTO studentDTO) {
        // 1. Tìm Role STUDENT
        Role studentRole = roleRepository.findByRole("ROLE_STUDENT");
        if (studentRole == null) {
            throw new RuntimeException("Không tìm thấy ROLE_STUDENT trong hệ thống.");
        }

        // 2. Tạo User
        String generatedUsername = "STUDENT_" + studentDTO.getFullName().toUpperCase().replaceAll("\\s+", "_");
        User user = new User();
        user.setUsername(generatedUsername);
        user.setEnabled(true);
        user.setEmail(studentDTO.getEmail());
        user.setRoleList(Collections.singletonList(studentRole));

        userRepository.save(user); // Lưu trước để gán cho Student

        // 3. Lấy Course và Class từ ID
        Course course = courseRepository.findById(studentDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Course với ID: " + studentDTO.getCourseId()));
        SchoolClass schoolClass = classRepository.findById(studentDTO.getSchoolClassId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Class với ID: " + studentDTO.getSchoolClassId()));

        // 4. Tạo Student
        Student student = new Student();
        student.setFullName(studentDTO.getFullName());
        student.setGender(studentDTO.getGender());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setAddress(studentDTO.getAddress());
        student.setUser(user);
        student.setCourseId(course);
        student.setSchoolClass(schoolClass);

        studentRepository.save(student); // Lưu student trước khi tạo password

        // 5. Tạo mật khẩu mặc định và mã hóa
        String rawPassword = studentDTO.getFullName() + student.getId();
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        userRepository.save(user); // Cập nhật password
    }

    public StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFullName(student.getFullName());
        dto.setGender(student.getGender());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setAddress(student.getAddress());
        dto.setEmail(student.getUser().getEmail());
        dto.setUserName(student.getUser().getUsername());
        dto.setCourseId(student.getCourseId().getId());
        dto.setSchoolClassId(student.getSchoolClass().getId());
        return dto;
    }


    @Transactional
    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id);
        List<Score> scores = scoreRepository.findByStudentId(id);
        scoreRepository.deleteByStudentId(id);
        scoreRepository.deleteAll(scores);
        System.out.println(student);
        studentRepository.delete(student);
    }

    public List<Score> getScoreList(int studentId) {
        return scoreRepository.findByStudentId(studentId);
    }

    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    public Subject findSubjectById(int id) {
        return subjectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Subject not found"));
    }

    public void addScore(ScoreDTO scoreDTO) {
        List<Score> scoreList = scoreRepository.findByStudentId(scoreDTO.getStudentId());
        System.out.println(scoreList);
        Score score = new Score();
        score.setStudent(findById(scoreDTO.getStudentId()));
        score.setSubject(findSubjectById(scoreDTO.getSubjectId()));
        score.setScore(scoreDTO.getScore());
        scoreList.add(score);
        scoreRepository.saveAll(scoreList);
    }


    @Transactional
    public void deleteScore(int id) {
        scoreRepository.deleteBySubjectId(id);
    }

    @Transactional
    public void updateStudent(StudentDTO studentDTO) {
        // 1. Lấy student cũ từ DB
        Student student = studentRepository.findById(studentDTO.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên có ID: " + studentDTO.getId()));

        // 2. Cập nhật thông tin cơ bản
        student.setFullName(studentDTO.getFullName());
        student.setGender(studentDTO.getGender());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setAddress(studentDTO.getAddress());

        // 3. Cập nhật course và class
        Course course = courseRepository.findById(studentDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học"));
        SchoolClass schoolClass = classRepository.findById(studentDTO.getSchoolClassId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học"));
        student.setCourseId(course);
        student.setSchoolClass(schoolClass);

        // 4. Cập nhật user liên quan (nếu bạn cho phép sửa email)
        User user = student.getUser();
        user.setEmail(studentDTO.getEmail());
    }


    public void updateScore(ScoreDTO scoreDTO) {
        Score score = scoreRepository.findBySubjectId(scoreDTO.getSubjectId());
        score.setScore(scoreDTO.getScore());
        scoreRepository.save(score);
    }
}
