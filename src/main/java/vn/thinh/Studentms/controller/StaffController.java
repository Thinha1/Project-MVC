package vn.thinh.Studentms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.thinh.Studentms.model.DTO.UserDTO;
import vn.thinh.Studentms.model.entity.Role;
import vn.thinh.Studentms.model.entity.User;
import vn.thinh.Studentms.model.repository.RoleRepository;
import vn.thinh.Studentms.service.RoleService;
import vn.thinh.Studentms.service.StudentService;
import vn.thinh.Studentms.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final StudentService studentService;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    @Autowired
    public StaffController(StudentService studentService, UserService userService, RoleRepository roleRepository, RoleService roleService) {
        this.studentService = studentService;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @GetMapping("/showStaff")
    public String showStaff(Model model) {
        List<User> users = userService.showList();
        List<User> staffs = users.stream().filter(user -> !user.getUsername().contains("STUDENT")).toList();
        model.addAttribute("staffs", staffs);
        return "/user/staff/staffList";
    }

    @GetMapping("/showForm")
    public String createStaff(UserDTO user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getRole());
        model.addAttribute("classList", userService.getClasList());
        return "/user/staff/createStaff";
    }

    @PostMapping("/create")
    public String createStaff(UserDTO user){
        userService.registerForStaff(user);
        return "redirect:/staff/showStaff";
    }

    @GetMapping("/edit/{id}")
    public String showEditStaff(@PathVariable("id") int id, Model model){
        User user = userService.findById(id);
        UserDTO userDTO = userService.convertToDTO(user);
        model.addAttribute("staff", userDTO);
        model.addAttribute("roles", roleService.getRole());
        model.addAttribute("classList", userService.getClasList());
        String currentClass = user.getSchoolClassList().get(0).getCode();
        model.addAttribute("currentClass", currentClass);
        model.addAttribute("currentClass", userService.findByTeacherId(id));
        System.out.println(userService.findByTeacherId(id));
        return "/user/staff/editStaff";
    }

    @PostMapping("/update")
    public String updateStaff(@ModelAttribute("staff") UserDTO userDTO, Model model){
        userService.updateStaff(userDTO);
        return "redirect:/staff/showStaff";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable("id") int id, Model model){
        userService.deleteStaff(id);
        return "redirect:/staff/showStaff";
    }


}
