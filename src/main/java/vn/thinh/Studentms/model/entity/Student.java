package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    @Column(name = "student_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "fullName")
    private String fullName;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClassId;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private Gender gender;
    @Column(name = "student_address")
    private String address;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Score> scores;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    @JoinColumn(name = "course_id")
    private Course courseId;

    public enum Gender {
        MALE, FEMALE
    }

    public Student() {
    }

    public Student(User user, SchoolClass schoolClassId, LocalDate dateOfBirth, Gender gender, String address, List<Score> scores) {
        this.user = user;
        this.schoolClassId = schoolClassId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.scores = scores;
    }
}
