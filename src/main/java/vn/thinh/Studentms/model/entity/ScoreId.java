package vn.thinh.Studentms.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ScoreId implements Serializable {
    private int subjectId;
    private int studentId;

    public ScoreId() {
    }

    public ScoreId(int subjectId, int studentId) {
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ScoreId scoreId = (ScoreId) o;
        return subjectId == scoreId.subjectId && studentId == scoreId.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, studentId);
    }
}
