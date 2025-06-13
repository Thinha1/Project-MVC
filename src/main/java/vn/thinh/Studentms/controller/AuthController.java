package vn.thinh.Studentms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.thinh.Studentms.model.DTO.UserDTO;
import vn.thinh.Studentms.model.entity.Role;
import vn.thinh.Studentms.model.repository.RoleRepository;
import vn.thinh.Studentms.service.RoleService;
import vn.thinh.Studentms.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;
    public AuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("roles", roleService.getRole());
        model.addAttribute("user", new UserDTO());
        return "user/staff/registerStaff";
    }

    @PostMapping("/staff/register")
    public String registerStaff(UserDTO registerUser){
        userService.registerForStaff(registerUser);
        return "redirect:/auth/login";
    }

    @GetMapping("/showLogin")
    public String showLoginForm(){
        return "/login";
    }
}
