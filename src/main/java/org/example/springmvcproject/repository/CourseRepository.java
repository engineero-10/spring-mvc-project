package org.example.springmvcproject.repository;

import org.example.springmvcproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByCourseName(String id);

    List<Course> findAllByInstructor_Id(int instructorId);

    Course findByCourseIdAndInstructor_Id(int courseId, int instructorId);
}
