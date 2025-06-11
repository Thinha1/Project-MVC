package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.thinh.Studentms.model.entity.Score;
import vn.thinh.Studentms.model.entity.Student;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findByStudentId(int studentId);
}
