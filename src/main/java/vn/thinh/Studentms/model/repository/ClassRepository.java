package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.thinh.Studentms.model.entity.SchoolClass;

public interface ClassRepository extends JpaRepository<SchoolClass, Integer> {
}
