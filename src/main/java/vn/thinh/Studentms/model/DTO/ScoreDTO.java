package vn.thinh.Studentms.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreDTO {
    private Integer studentId;
    private Integer subjectId;
    private Double score;
}
