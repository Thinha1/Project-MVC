package vn.thinh.Studentms.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    @Column(name = "role_name", length = 100)
    private String role;
    @Column(name = "role_description", length = 100)
    private String description;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "role_id")
    , inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userList;

    public Role() {
    }
    public Role(String role, String description, List<User> userList) {
        this.role = role;
        this.description = description;
        this.userList = userList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
