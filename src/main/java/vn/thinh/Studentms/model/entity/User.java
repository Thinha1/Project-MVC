package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_name", length = 100)
    private String username;
    @Column(name = "user_password", length = 100)
    private String password;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, mappedBy = "userList")
    private Set<Role> roles;
    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    }, mappedBy = "user")
    private Staff staff;
    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    }, mappedBy = "user")
    private Student student;

    public User() {
    }

    public User(String username, String password, Set<Role> roles, Staff staff, Student student) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.staff = staff;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
