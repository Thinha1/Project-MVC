package vn.thinh.Studentms.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterUser {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String role;
    private List<String> roleList;
}
