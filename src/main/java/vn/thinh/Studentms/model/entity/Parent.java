package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Parent {
    @EmbeddedId
    private ParentId id;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinTable(name = "parent_student",
    joinColumns = {
            @JoinColumn(name = "parent_id"),
            @JoinColumn(name = "school_id")},
    inverseJoinColumns = @JoinColumn(name = "student_id"))

    private List<Student> studentList;
    private String name;
    private String phone;
    private String address;
    private String email;

    public Parent() {
    }

    public Parent(List<Student> studentList, String name, String phone, String address, String email) {
        this.studentList = studentList;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Parent(ParentId id, List<Student> studentList, String name, String phone, String address, String email) {
        this.id = id;
        this.studentList = studentList;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public ParentId getId() {
        return id;
    }

    public void setId(ParentId id) {
        this.id = id;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
