package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;

@Entity
public class Score {
    @EmbeddedId
    private ScoreId id;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "score")
    private double score;

    public Score() {
    }

    public Score(Subject subject, Student student, double score) {
        this.subject = subject;
        this.student = student;
        this.score = score;
    }

    public Score(ScoreId id, Subject subject, Student student, double score) {
        this.id = id;
        this.subject = subject;
        this.student = student;
        this.score = score;
    }

    public ScoreId getId() {
        return id;
    }

    public void setId(ScoreId id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
