package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.thinh.Studentms.model.entity.SchoolClass;

import java.util.List;

public interface ClassRepository extends JpaRepository<SchoolClass, Integer> {
    SchoolClass findByName(String className);

    SchoolClass findByCode(String classCode);
}
