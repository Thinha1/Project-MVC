package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int id;
    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Column(name = "staff_name", length = 100)
    private String name;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinTable(name = "staff_roles"
    , joinColumns = {@JoinColumn(name = "staff_id")}
    , inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;
    @Column(name = "staff_gender", length = 10)
    private String gender;
    @Column(name = "staff_phone", length = 15)
    private String phone;
    @Column(name = "staff_address", length = 100)
    private String address;
    @Column(name = "staff_email", length = 100)
    private String email;
    @Column(name = "staff_dob")
    private Date dob;
    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, mappedBy = "principal")
    private School school;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, mappedBy = "head_teacher")
    private SchoolClass schoolClassTeaching;

    public Staff() {
    }

    public Staff(User user, String name, Set<Role> roles, String gender, String phone, String address, String email, Date dob, School school, SchoolClass schoolClassTeaching) {
        this.user = user;
        this.name = name;
        this.roles = roles;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.dob = dob;
        this.school = school;
        this.schoolClassTeaching = schoolClassTeaching;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public SchoolClass getSchoolClassTeaching() {
        return schoolClassTeaching;
    }

    public void setSchoolClassTeaching(SchoolClass schoolClassTeaching) {
        this.schoolClassTeaching = schoolClassTeaching;
    }
}
