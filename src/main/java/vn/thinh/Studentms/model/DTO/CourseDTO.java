package vn.thinh.Studentms.model.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.thinh.Studentms.model.entity.Student;
import vn.thinh.Studentms.model.entity.Subject;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private int id;
    private String name;
    private List<String> subject;
}
