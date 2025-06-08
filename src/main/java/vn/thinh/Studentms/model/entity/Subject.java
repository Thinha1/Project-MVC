package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinTable(name = "subject_teacher",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<Staff> staffList;

    @Column(name = "subject_name", length = 100)
    private String name;
    @Column(name = "subject_description", length = 100)
    private String description;
    @Column(name = "subject_exam_type", length = 100)
    private String examType;
    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, mappedBy = "subject")
    private List<Score> scoreList;

    public Subject() {
    }

    public Subject(List<Staff> staffList, String name, String description, String examType, List<Score> scoreList) {
        this.staffList = staffList;
        this.name = name;
        this.description = description;
        this.examType = examType;
        this.scoreList = scoreList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }
}
