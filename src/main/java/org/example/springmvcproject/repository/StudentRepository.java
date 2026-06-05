package org.example.springmvcproject.repository;

import org.example.springmvcproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Student getStudentsByEmail(String email);

    List<Student> findAllByInstructor_Id(int instructorId);

    Student findByIdAndInstructor_Id(int id, int instructorId);
}
