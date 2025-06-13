package vn.thinh.Studentms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.thinh.Studentms.model.DTO.ScoreDTO;
import vn.thinh.Studentms.model.DTO.StudentDTO;
import vn.thinh.Studentms.model.entity.*;
import vn.thinh.Studentms.model.repository.*;
import vn.thinh.Studentms.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;
    private StudentService studentService;
    private CourseRepository courseRepository;
    private ClassRepository classRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public StudentController(StudentService studentService, CourseRepository courseRepository, ClassRepository classRepository, SubjectRepository subjectRepository, StudentRepository studentRepository, ScoreRepository scoreRepository) {
        this.studentService = studentService;
        this.courseRepository = courseRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
    }


    @GetMapping("/showForm")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("classList", classRepository.findAll());
        return "/user/student/formStudent";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") StudentDTO studentDTO, Model model) {
        studentService.createStudent(studentDTO);
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "user/student/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateStudentForm(@PathVariable("id") int id, Model model){
        Student student = studentService.findById(id);
        StudentDTO studentDTO = studentService.convertToDTO(student);
        model.addAttribute("student", studentDTO);
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("classList", classRepository.findAll());
        return "/user/student/formEdit";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") StudentDTO studentDTO, Model model){
        studentService.updateStudent(studentDTO);
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "user/student/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model){
        studentService.deleteStudent(id);
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
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
        model.addAttribute("scores", scores);
        model.addAttribute("student", studentService.findById(id));
        return "user/student/scoreList";
    }

    @GetMapping("/addScore/{id}")
    public String getFormAddScore(@PathVariable("id") int id, Model model){
        Student student = studentService.findById(id);
        model.addAttribute("student", student);

        List<Subject> subjectList = subjectRepository.findAll();
        List<Subject> subjects = student.getScores().stream().map(Score::getSubject).toList();
        List<Subject> availableSubject = subjectList.stream().filter(s -> !subjects.contains(s)).toList();
        model.addAttribute("subjectList", availableSubject);
        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setStudentId(id);
        model.addAttribute("score", scoreDTO);
        return "/user/student/formAddScore";
    }

    @PostMapping("/scores/add")
    public String addScore(@ModelAttribute("score") ScoreDTO scoreDTO, Model model){
        studentService.addScore(scoreDTO);
        Student student = studentService.findById(scoreDTO.getStudentId());
        model.addAttribute("student", student);
        model.addAttribute("scores", studentService.findById(scoreDTO.getStudentId()).getScores());
        return "/user/student/scoreList";
    }


    @GetMapping("/scores/edit/{id}")
    public String showUpdateScoreForm(@PathVariable("id") int id, Model model){
        Score score = scoreRepository.findBySubjectId(id);
        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setStudentId(score.getStudent().getId());
        scoreDTO.setSubjectId(score.getSubject().getId());
        scoreDTO.setScore(score.getScore());
        model.addAttribute("score", scoreDTO);
        model.addAttribute("subjectList", subjectRepository.findAll());
        model.addAttribute("student", studentService.findById(score.getStudent().getId()));
        return "/user/student/formEditScore";
    }

    @PostMapping("/scores/update")
    public String updateScore(@ModelAttribute("score") ScoreDTO scoreDTO, Model model){
        studentService.updateScore(scoreDTO);
        Student student = studentService.findById(scoreDTO.getStudentId());
        model.addAttribute("student", student);
        model.addAttribute("scores", studentService.findById(scoreDTO.getStudentId()).getScores());
        return "/user/student/scoreList";
    }

    @GetMapping("/scores/delete/{studentId}/{subjectId}")
    public String deleteScore(@PathVariable("studentId") int studentId,@PathVariable("subjectId") int id, Model model) {
        studentService.deleteScore(id);
        Student student = studentService.findById(studentId);
        model.addAttribute("student", student);
        model.addAttribute("scores", studentService.findById(studentId).getScores());
        return "redirect:/student/view/" + studentId;
    }

}
