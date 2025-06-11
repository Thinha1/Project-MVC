package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int id;
    @Column(name = "course_name", unique = true)
    private String name;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    }, mappedBy = "courseId")
    private List<Student> studentList;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    }, mappedBy = "course")
    private List<Subject> subject;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    })
    private List<SchoolClass> schoolClassList;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentList=" + studentList +
                ", subject=" + subject +
                ", schoolClassList=" + schoolClassList +
                '}';
    }
}
