package org.example.springmvcproject.services;

import org.example.springmvcproject.entity.Course;
import org.example.springmvcproject.repository.CourseRepository;
import org.example.springmvcproject.scopes.SessionInstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServices {
    private final CourseRepository courseRepository;
    private final SessionInstructor sessionInstructor;

    public CourseServices(CourseRepository courseRepository, SessionInstructor sessionInstructor) {
        this.courseRepository = courseRepository;
        this.sessionInstructor = sessionInstructor;
    }

    public Course addCourse(Course course) {
        var courseEntity = courseRepository.findByCourseName(course.getCourseName());
        if (courseEntity == null) {
            course.setInstructor(sessionInstructor.getInstructor());
            return courseRepository.save(course);
        }
        return null;
    }

    public Course getCourseById(int id) {
        var instructor = sessionInstructor.getInstructor();
        if (instructor == null) {
            return null;
        }
        return courseRepository.findByCourseIdAndInstructor_Id(id, instructor.getId());
    }

    public List<Course> getAllCourses() {
        var instructor = sessionInstructor.getInstructor();
        if (instructor == null) {
            return List.of();
        }
        return courseRepository.findAllByInstructor_Id(instructor.getId());
    }

    public Course updateCourse(Course course) {
        var existing = getCourseById(course.getCourseId());
        if (existing == null) {
            return null;
        }
        course.setInstructor(existing.getInstructor());
        return courseRepository.save(course);
    }
    @Transactional
    public void deleteCourse(int id) {
        var existing = getCourseById(id);
        if (existing != null) {
            courseRepository.delete(existing);
        }
    }
}
