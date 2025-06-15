package vn.thinh.Studentms.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
    private Integer classId;
    private String classCode;
    private String description;
    private Integer courseId;
    private Integer teacherId;
}
