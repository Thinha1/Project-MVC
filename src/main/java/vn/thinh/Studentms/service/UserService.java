package vn.thinh.Studentms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.thinh.Studentms.model.DTO.UserDTO;
import vn.thinh.Studentms.model.entity.Role;
import vn.thinh.Studentms.model.entity.User;
import vn.thinh.Studentms.model.repository.RoleRepository;
import vn.thinh.Studentms.model.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void registerForStaff(UserDTO registerUser){
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

    public List<User> showList(){
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

    public User findById(int id){
        return userRepository.findById(id).orElseThrow();
    }

    public UserDTO convertToDTO(User user){
        UserDTO userDTO = new UserDTO();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(passwordEncoder.encode(user.getPassword()));
        userDTO.setFullname(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setRoleList(user.getRoleList().stream().map(Role::getRole).toList());
        return userDTO;
    }

    public void updateStaff(UserDTO userDTO){
        User newUser = findById(userDTO.getId());
        newUser.setUsername(userDTO.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setFullName(userDTO.getFullname());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPhone(userDTO.getPhone());
        newUser.setEnabled(true);
        newUser.setRoleList(roleRepository.findByRoleIn(userDTO.getRoleList()));
        userRepository.save(newUser);
    }

    public void deleteStaff(int id){
        userRepository.deleteById(id);
    }
}
