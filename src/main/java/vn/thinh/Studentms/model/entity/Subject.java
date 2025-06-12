package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "subject_code", unique = true)
    private String code;
    @Column(name = "subject_name")
    private String name;
    @Column(name = "subject_description")
    private String description;
    @Column(name = "subject_credits")
    private int credits;
    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Score> scoreList;
    @Column(name = "exam_type")
    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE
    })
    @JoinColumn(name = "course_id")
    private Course course;

    public enum ExamType {
        TRAC_NGHIEM, TU_LUAN, BAO_CAO
    }

    public Subject() {
    }

    public Subject(String code, String name, String description, int credits, List<Score> scoreList, ExamType examType) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.scoreList = scoreList;
        this.examType = examType;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits +
                ", examType=" + examType +
                ", course=" + course +
                '}';
    }
}
