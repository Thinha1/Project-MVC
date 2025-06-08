package vn.thinh.Studentms.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

import java.util.Objects;

@Embeddable
public class ParentId {
    private int studentId;
    private int parentId;

    public ParentId() {
    }

    public ParentId(int studentId, int parentId) {
        this.studentId = studentId;
        this.parentId = parentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParentId parentId1 = (ParentId) o;
        return studentId == parentId1.studentId && parentId == parentId1.parentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, parentId);
    }
}
