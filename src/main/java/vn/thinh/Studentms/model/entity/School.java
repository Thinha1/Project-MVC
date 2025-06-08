package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class School {
    @Id
    @Column(name = "school_id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @Column(name = "school_name", length = 100)
    private String name;
    @Column(name = "school_address", length = 100)
    private String address;
    @Column(name = "school_phone", length = 15)
    private int phone;
    @Column(name = "school_email", length = 100)
    private String email;
    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "principal_id")
    private Staff principal;
    @OneToMany(mappedBy = "school")
    private List<SchoolClass> schoolClassList;

    public School() {
    }

    public School(String name, String address, int phone, String email, Staff principal, List<SchoolClass> schoolClassList) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.principal = principal;
        this.schoolClassList = schoolClassList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Staff getPrincipal() {
        return principal;
    }

    public void setPrincipal(Staff principal) {
        this.principal = principal;
    }

    public List<SchoolClass> getSchoolClassList() {
        return schoolClassList;
    }

    public void setSchoolClassList(List<SchoolClass> schoolClassList) {
        this.schoolClassList = schoolClassList;
    }
}
