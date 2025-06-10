package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.thinh.Studentms.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
