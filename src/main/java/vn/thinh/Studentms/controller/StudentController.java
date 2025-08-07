package vn.thinh.Studentms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.thinh.Studentms.model.DTO.*;
import vn.thinh.Studentms.model.entity.*;
import vn.thinh.Studentms.model.repository.*;
import vn.thinh.Studentms.service.StudentService;
import vn.thinh.Studentms.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final ScoreRepository scoreRepository;
    private final UserService userService;
    private StudentService studentService;
    private CourseRepository courseRepository;
    private ClassRepository classRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public StudentController(StudentService studentService, CourseRepository courseRepository, ClassRepository classRepository, SubjectRepository subjectRepository, ScoreRepository scoreRepository, UserService userService) {
        this.studentService = studentService;
        this.courseRepository = courseRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
        this.scoreRepository = scoreRepository;
        this.userService = userService;
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
        return "redirct:/user/student/list";
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

    @GetMapping("/showClass")
    public String showClass(Model model){
        List<SchoolClass> schoolClasses = studentService.showClassList();
        model.addAttribute("schoolClasses", schoolClasses);
        return "/user/student/class/classList";
    }

    @GetMapping("/class/showForm")
    public String showCreateClassForm(Model model) {
        model.addAttribute("class", new ClassDTO());
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courseNames", courses);
        List<User> users = userService.getAvailableUser();
        model.addAttribute("users", users);
        return "/user/student/class/formAddClass";
    }

    @PostMapping("/class/add")
    public String addClass(@ModelAttribute("class") ClassDTO classDTO, Model model){
        studentService.addClass(classDTO);
        return "redirect:/student/showClass";
    }

    @GetMapping("/class/edit/{id}")
    public String showEditClassForm(@PathVariable("id") int id, Model model){
        SchoolClass schoolClass = classRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Class not found"));
        ClassDTO classDTO = new ClassDTO();
        studentService.convertToDTO(schoolClass, classDTO);
        model.addAttribute("class", classDTO);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courseNames", courses);
        List<User> users = userService.getAvailableUser();
        model.addAttribute("users", users);
        return "/user/student/class/formEditClass";
    }

    @PostMapping("/class/update")
    public String updateClass(@ModelAttribute("class") ClassDTO classDTO, Model model){
        studentService.updateClass(classDTO);
        return "redirect:/student/showClass";
    }

    @GetMapping("/class/delete/{id}")
    public String deleteClass(@PathVariable("id") int id, Model model){
        studentService.deleteClass(id);
        return "redirect:/student/showClass";
    }

    @GetMapping("/showCourse")
    public String showCourseList(Model model){
        model.addAttribute("courses", studentService.getCourseList());
        model.addAttribute("subjects", studentService.getSubjectNameList());
        return "/user/student/course/courseList";
    }

    @GetMapping("/course/showForm")
    public String showCreateCourseForm(Model model) {
        model.addAttribute("course", new CourseDTO());
        return "/user/student/course/formAddCourse";
    }

    @PostMapping("/course/add")
    public String addCourse(@ModelAttribute("course") CourseDTO courseDTO){
        studentService.addCourse(courseDTO);
        return "redirect:/student/showCourse";
    }

    @GetMapping("/course/edit/{id}")
    public String editCourse(@PathVariable("id") int id, Model model){
        Course course = studentService.findCourseById(id);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        model.addAttribute("course", courseDTO);
        return "/user/student/course/formEditCourse";
    }

    @PostMapping("/course/update")
    public String updateCourse(@ModelAttribute("course") CourseDTO courseDTO){
        studentService.updateCourse(courseDTO);
        return "redirect:/student/showCourse";
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model){
        studentService.deleteCourse(id);
        return "redirect:/student/showCourse";
    }

    @GetMapping("/course/viewSubject/{id}")
    public String showSubjectList(@PathVariable("id") int courseId, Model model){
        List<Subject> subjects = studentService.getSubjectListByCourseId(courseId);
        model.addAttribute("subjects", subjects);
        model.addAttribute("currentCourse", courseRepository.findCourseById(courseId));
        return "/user/student/subject/subjectList";
    }

    @GetMapping("/course/subject/showAddForm")
    public String showAddSubjectForm(@RequestParam("id") int courseId, Model model){
        model.addAttribute("subject", new SubjectDTO());
        model.addAttribute("currentCourse", courseRepository.findCourseById(courseId));
        model.addAttribute("examType", Subject.ExamType.values());
        return "/user/student/subject/formAddSubject";
    }

    @PostMapping("/course/subject/add/{id}")
    public String addSubject(@ModelAttribute("subject") SubjectDTO subjectDTO, @PathVariable("id") int courseId){
        studentService.addSubject(subjectDTO, courseId);
        return "redirect:/student/course/viewSubject/" + courseId;
    }

    @GetMapping("/course/subject/edit/{id}")
    public String editSubject(@PathVariable("id") int subjectId, Model model){
        Subject subject = studentService.findSubjectById(subjectId);
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setCredits(subject.getCredits());
        subjectDTO.setCode(subject.getCode());
        subjectDTO.setCourseName(subject.getCourse().getName());
        subjectDTO.setExamType(subject.getExamType().name());
        model.addAttribute("subject", subjectDTO);
        model.addAttribute("currentCourse", subject.getCourse());
        model.addAttribute("examType", Subject.ExamType.values());
        return "/user/student/subject/formEditSubject";
    }

    @PostMapping("/course/subject/update/{id}")
    public String updateSubject(@ModelAttribute("subject") SubjectDTO subjectDTO, @PathVariable("id") int courseId){
        studentService.updateSubject(subjectDTO, subjectDTO.getId());
        return "redirect:/student/course/viewSubject/" + courseId;
    }

}
