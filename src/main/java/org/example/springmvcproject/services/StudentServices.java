package org.example.springmvcproject.services;

import org.example.springmvcproject.entity.Student;
import org.example.springmvcproject.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServices {
    private final StudentRepository studentRepository;
    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return null;
    }

    public Student addStudent(Student student) {
        var isStudent = studentRepository.findByEmail(student.getEmail());
        if (isStudent != null) {
            return null;
        }
        return studentRepository.save(student);
    }
}
