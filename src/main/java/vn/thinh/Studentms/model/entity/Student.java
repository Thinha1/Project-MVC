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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "student_code", unique = true)
    private String studentCode;

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

    @OneToMany(mappedBy = "student", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    private List<Score> scores;

    public enum Gender {
        MALE, FEMALE
    }

    public Student() {
    }

    public Student(User user, String studentCode, SchoolClass schoolClassId, LocalDate dateOfBirth, Gender gender, String address, List<Score> scores) {
        this.user = user;
        this.studentCode = studentCode;
        this.schoolClassId = schoolClassId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.scores = scores;
    }
}
