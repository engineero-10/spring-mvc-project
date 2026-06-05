package org.example.springmvcproject.repository;

import org.example.springmvcproject.entity.Course;
import org.example.springmvcproject.services.CourseServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    public Course findByCourseName(String id);
}
