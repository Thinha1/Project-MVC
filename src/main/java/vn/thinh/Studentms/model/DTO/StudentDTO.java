package vn.thinh.Studentms.model.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.thinh.Studentms.model.entity.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private int id;
    private String userName;
    private String fullName;
    private String email;
    private String schoolClassId;
    private LocalDate dateOfBirth;
    private Student.Gender gender;
    private String address;
    private List<String> subjectNames;
    private List<Double> scores;
    private String courseName;
    public enum Gender {
        MALE, FEMALE
    }
}
