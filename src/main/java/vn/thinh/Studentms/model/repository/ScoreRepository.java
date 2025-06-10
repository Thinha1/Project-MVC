package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.thinh.Studentms.model.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
}
