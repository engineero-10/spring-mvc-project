package org.example.springmvcproject.services;

import org.example.springmvcproject.entity.Course;
import org.example.springmvcproject.entity.Student;
import org.example.springmvcproject.repository.StudentRepository;
import org.example.springmvcproject.scopes.SessionInstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServices {
    private final StudentRepository studentRepository;
    private final SessionInstructor sessionInstructor;

    public StudentServices(StudentRepository studentRepository, SessionInstructor sessionInstructor) {
        this.studentRepository = studentRepository;
        this.sessionInstructor = sessionInstructor;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        var courseEntity = studentRepository.getStudentsByEmail(student.getEmail());
        if (courseEntity == null) {
            student.setInstructor(sessionInstructor.getInstructor());
            return studentRepository.save(student);
        }
        return null;
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
