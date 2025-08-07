package vn.thinh.Studentms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.thinh.Studentms.model.entity.Role;
import vn.thinh.Studentms.model.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<String> getRole(){
        List<Role> roles = roleRepository.findAll();
        List<String> roleNames = roles.stream()
                .map(Role::getRole)
                .toList();
        List<String> notAvailaleRoles = Arrays.asList("ROLE_STUDENT", "ROLE_ADMIN");
        return roleNames.stream().filter(roleName -> !notAvailaleRoles.contains(roleName)).toList();
    }
}
