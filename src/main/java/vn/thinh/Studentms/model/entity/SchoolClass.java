package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;

@Entity
public class Class {
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

}
