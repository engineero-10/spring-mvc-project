package org.example.springmvcproject.controller;

import org.example.springmvcproject.entity.Course;
import org.example.springmvcproject.repository.CourseRepository;
import org.example.springmvcproject.services.CourseServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    private CourseServices courseServices;
    public CoursesController(CourseServices courseServices) {
        this.courseServices = courseServices;
    }

    @GetMapping("/add")
    public String course(Model model){
        model.addAttribute("course", new Course());
        return "addCourse";
    }
    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course, Model model){
        var courses = courseServices.addCourse(course);
        if(courses==null){
            model.addAttribute("error",true);
            return "addCourse";
        }
        return "redirect:/home";
    }
    }
