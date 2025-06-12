package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.thinh.Studentms.model.entity.Score;
import vn.thinh.Studentms.model.entity.Student;
import vn.thinh.Studentms.model.entity.Subject;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findByStudentId(int studentId);

    Score findBySubjectId(Integer subjectId);

    void deleteByStudentId(int id);

    void deleteBySubjectId(int id);

    void deleteBySubjectIdAndStudentId(int subjectId, int studentId);
}
