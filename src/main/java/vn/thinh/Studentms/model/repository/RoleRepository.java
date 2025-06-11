package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.thinh.Studentms.model.entity.Role;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByRoleIn(List<String> roles);

    Role findByRole(String role);
}
