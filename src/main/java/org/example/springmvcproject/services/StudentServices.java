package org.example.springmvcproject.services;

import org.example.springmvcproject.entity.Student;
import org.example.springmvcproject.repository.StudentRepository;
import org.example.springmvcproject.scopes.SessionInstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var instructor = sessionInstructor.getInstructor();
        if (instructor == null) {
            return List.of();
        }
        return studentRepository.findAllByInstructor_Id(instructor.getId());
    }

    public Student getStudentById(int id) {
        var instructor = sessionInstructor.getInstructor();
        if (instructor == null) {
            return null;
        }
        return studentRepository.findByIdAndInstructor_Id(id, instructor.getId());
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
        var existing = getStudentById(student.getId());
        if (existing == null) {
            return null;
        }
        student.setInstructor(existing.getInstructor());
        return studentRepository.save(student);
    }
    @Transactional
    public void deleteStudent(int id) {
        var existing = getStudentById(id);
        if (existing != null) {
            studentRepository.delete(existing);
        }
    }
}
