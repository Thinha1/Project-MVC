package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.thinh.Studentms.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    String findIdByFullName(String fullName);
    Student findById(int id);

    void deleteById(int id);
}
