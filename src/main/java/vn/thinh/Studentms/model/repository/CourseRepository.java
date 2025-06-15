package vn.thinh.Studentms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.thinh.Studentms.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByName(String courseName);

    Course findCourseById(int id);
}
