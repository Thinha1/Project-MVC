package vn.thinh.Studentms.model.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.thinh.Studentms.model.entity.Score;
import vn.thinh.Studentms.model.entity.Subject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private int id;
    private String code;
    private String name;
    private int credits;
    private String examType;
    private String courseName;
}
