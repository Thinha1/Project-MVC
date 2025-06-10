package vn.thinh.Studentms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.thinh.Studentms.model.entity.Student;
import vn.thinh.Studentms.model.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getAll(){
        return studentRepository.findAll();
    }
}
