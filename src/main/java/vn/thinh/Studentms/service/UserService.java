package vn.thinh.Studentms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.thinh.Studentms.model.DTO.RegisterUser;
import vn.thinh.Studentms.model.entity.User;
import vn.thinh.Studentms.model.repository.RoleRepository;
import vn.thinh.Studentms.model.repository.UserRepository;

@Service
public class UserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void registerForStaff(RegisterUser registerUser){
        User newUser = new User();
        newUser.setUsername(registerUser.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        newUser.setFullName(registerUser.getFullname());
        newUser.setEmail(registerUser.getEmail());
        newUser.setPhone(registerUser.getPhone());
        newUser.setEnabled(true);
        newUser.setRoleList(roleRepository.findByRoleIn(registerUser.getRoleList()));
        userRepository.save(newUser);
    }
}
