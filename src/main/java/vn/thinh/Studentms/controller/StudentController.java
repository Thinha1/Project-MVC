package vn.thinh.Studentms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.thinh.Studentms.model.DTO.StudentDTO;
import vn.thinh.Studentms.model.entity.Course;
import vn.thinh.Studentms.model.entity.SchoolClass;
import vn.thinh.Studentms.model.entity.Score;
import vn.thinh.Studentms.model.entity.Student;
import vn.thinh.Studentms.model.repository.ClassRepository;
import vn.thinh.Studentms.model.repository.CourseRepository;
import vn.thinh.Studentms.model.repository.StudentRepository;
import vn.thinh.Studentms.service.StudentService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
    private CourseRepository courseRepository;
    private ClassRepository classRepository;

    @Autowired
    public StudentController(StudentService studentService, CourseRepository courseRepository, ClassRepository classRepository) {
        this.studentService = studentService;
        this.courseRepository = courseRepository;
        this.classRepository = classRepository;
    }


    @GetMapping("/showForm")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new StudentDTO());
        List<Course> courses = courseRepository.findAll();
        List<String> courseNames = courses.stream()
                .map(Course::getName).toList();
        List<SchoolClass> classList = classRepository.findAll();
        List<String> classNames = classList.stream()
                        .map(SchoolClass::getCode).toList();
        model.addAttribute("courses", courseNames);
        model.addAttribute("classList", classNames);
        return "/user/student/formCreate";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        return "user/student/list";
    }

    @GetMapping("/showStudentList")
    public String showStudentList(Model model){
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "user/student/list";
    }

    @GetMapping("/view/{id}")
    public String showScoreList(@PathVariable("id") int id, Model model){
        List<Score> scores = studentService.getScoreList(id);
        System.out.println(scores);
        model.addAttribute("scores", scores);
        model.addAttribute("student", studentService.findById(id));
        return "user/student/scoreList";
    }


}
