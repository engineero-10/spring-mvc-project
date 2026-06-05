package org.example.springmvcproject.repository;

import org.example.springmvcproject.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    Enrollment findByCourse_CourseIdAndAndStudent_Id(int courseId, int studentId);

    boolean existsByStudent_IdAndCourse_CourseIdAndIdNot(
            int studentId,
            int courseId,
            int id
    );

    List<Enrollment> findAllByCourse_Instructor_Id(int instructorId);

    Enrollment findByIdAndCourse_Instructor_Id(int id, int instructorId);

    void deleteByIdAndCourse_Instructor_Id(int id, int instructorId);
}