package vn.thinh.Studentms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.thinh.Studentms.model.DTO.StudentDTO;
import vn.thinh.Studentms.model.entity.*;
import vn.thinh.Studentms.model.repository.*;

import java.util.ArrayList;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentService(RoleRepository roleRepository, UserRepository userRepository, StudentRepository studentRepository, CourseRepository courseRepository, ClassRepository classRepository, ScoreRepository scoreRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.classRepository = classRepository;
        this.scoreRepository = scoreRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void createStudent(StudentDTO studentDTO) {
        //SET ROLE
        Role role = roleRepository.findByRole("ROLE_STUDENT");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        //SET USER
        User user = new User();
        String username = "STUDENT_" + studentDTO.getFullName().toUpperCase().replaceAll("\\s+", "_");
        user.setUsername(username);
        user.setEnabled(true);
        user.setRoleList(roles);
        user.setEmail(studentDTO.getEmail());
        userRepository.save(user);
        //SET STUDENT
        Student student = new Student();
        student.setFullName(studentDTO.getFullName());
        student.setGender(studentDTO.getGender());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setAddress(studentDTO.getAddress());
        student.setUser(user);
        String className = studentDTO.getSchoolClassId();
        SchoolClass schoolClass = classRepository.findByCode(className);
        student.setSchoolClassId(schoolClass);
        String courseName = studentDTO.getCourseName();
        Course course = courseRepository.findByName(courseName);
        student.setCourseId(course);
        studentRepository.save(student);
        String rawPassword = studentDTO.getFullName() + student.getId();
        user.setPassword(bCryptPasswordEncoder.encode(rawPassword));
        userRepository.save(user);
    }

    public List<Score> getScoreList(int studentId){
        return scoreRepository.findByStudentId(studentId);
    }

    public Student findById(int id){
        return studentRepository.findById(id);
    }
}
