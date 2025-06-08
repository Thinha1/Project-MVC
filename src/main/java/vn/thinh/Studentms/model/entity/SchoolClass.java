package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int id;
    @Column(name = "class_name", length = 100)
    private String name;
    @Column(name = "class_grade", length = 10)
    private String grade;
    @Column(name = "class_year", length = 10)
    private int year;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "school_id")
    private School school;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "staff_id")
    private Staff head_teacher;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, mappedBy = "schoolClass")
    private List<Student> studentList;

    public SchoolClass() {
    }

    public SchoolClass(String name, String grade, int year, School school, Staff head_teacher, List<Student> studentList) {
        this.name = name;
        this.grade = grade;
        this.year = year;
        this.school = school;
        this.head_teacher = head_teacher;
        this.studentList = studentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Staff getHead_teacher() {
        return head_teacher;
    }

    public void setHead_teacher(Staff head_teacher) {
        this.head_teacher = head_teacher;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
