package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_fullname")
    private String fullName;
    @Column(name = "user_phone")
    private String phone;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id")
    , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList;

    @Column(name = "user_enable")
    private boolean enabled;

    // OneToOne với Student nếu là STUDENT
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Student student;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<SchoolClass> schoolClassList;


    public User() {
    }

    public User(String username, String password, String email, String fullName, String phone, List<Role> roles, boolean enabled, Student student, List<SchoolClass> schoolClassList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.roleList = roles;
        this.enabled = enabled;
        this.student = student;
        this.schoolClassList = schoolClassList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", roleList=" + roleList +
                ", enabled=" + enabled +
                '}';
    }
}

