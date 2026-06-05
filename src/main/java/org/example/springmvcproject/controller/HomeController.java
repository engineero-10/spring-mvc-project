package org.example.springmvcproject.controller;

import org.example.springmvcproject.services.CourseServices;
import org.example.springmvcproject.services.StudentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final CourseServices courseServices;
    private final StudentServices studentServices;

    public HomeController(CourseServices courseServices, StudentServices studentServices) {
        this.courseServices = courseServices;
        this.studentServices = studentServices;
    }

    @GetMapping()
    public String home(Model model) {
        var courses = courseServices.getAllCourses();
        var students = studentServices.getAllStudents();
        model.addAttribute("courses", courses);
        model.addAttribute("students", students);
        return "home";
    }
}
