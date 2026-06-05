package org.example.springmvcproject.services;

import org.example.springmvcproject.entity.Course;
import org.example.springmvcproject.repository.CourseRepository;
import org.example.springmvcproject.scopes.SessionInstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServices {
    private final CourseRepository courseRepository;
    private final SessionInstructor sessionInstructor;
    public CourseServices(CourseRepository courseRepository , SessionInstructor sessionInstructor) {
        this.courseRepository = courseRepository;
        this.sessionInstructor = sessionInstructor;
    }

    public Course addCourse(Course course){
       var courseEntity = courseRepository.findByCourseName(course.getCourseName());
       if(courseEntity == null){
           course.setInstructor(sessionInstructor.getInstructor());
           return courseRepository.save(course);
       }
       return null;
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
}
