package vn.thinh.Studentms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.thinh.Studentms.model.DTO.RegisterUser;
import vn.thinh.Studentms.model.entity.Role;
import vn.thinh.Studentms.model.repository.RoleRepository;
import vn.thinh.Studentms.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        List<Role> roles = roleRepository.findAll();
        List<String> roleNames = roles.stream()
                        .map(Role::getRole)
                                .collect(Collectors.toList());
        model.addAttribute("roles", roleNames);
        model.addAttribute("user", new RegisterUser());
        return "user/staff/registerStaff";
    }

    @PostMapping("/staff/register")
    public String registerStaff(RegisterUser registerUser){
        userService.registerForStaff(registerUser);
        return "redirect:/auth/login";
    }

    @GetMapping("/showLogin")
    public String showLoginForm(){
        return "/login";
    }
}
