package org.example.springmvcproject.repository;

import org.example.springmvcproject.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    Enrollment findByCourse_CourseIdAndAndStudent_Id(int courseId, int studentId);

    boolean existsByStudent_IdAndCourse_CourseIdAndIdNot(
            int studentId,
            int courseId,
            int id
    );
}