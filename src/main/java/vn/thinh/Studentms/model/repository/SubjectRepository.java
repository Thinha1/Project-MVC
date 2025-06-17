package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.thinh.Studentms.model.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findSubjectById(int subjectId);
}
