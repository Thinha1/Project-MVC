package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "classes")
@Getter
@Setter
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "class_code", unique = true)
    private String code;
    @Column(name = "class_name")
    private String name;
    @Column(name = "class_description")
    private String description;
    @OneToMany(mappedBy = "schoolClass", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    private List<Student> students;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    @JoinColumn(name = "course_id")
    private Course course;

    public SchoolClass() {
    }

    public SchoolClass(String code, String name, String description, List<Student> students, User teacher) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
