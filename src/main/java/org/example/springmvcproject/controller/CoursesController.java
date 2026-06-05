package org.example.springmvcproject.controller;

import org.example.springmvcproject.entity.Course;
import org.example.springmvcproject.services.CourseServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    private final CourseServices courseServices;

    public CoursesController(CourseServices courseServices) {
        this.courseServices = courseServices;
    }

    @GetMapping("/add")
    public String course(Model model) {
        model.addAttribute("course", new Course());
        return "addCourse";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course, Model model) {
        var courses = courseServices.addCourse(course);
        if (courses == null) {
            model.addAttribute("error", true);
            return "addCourse";
        }
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Course existingCouser = courseServices.getCourseById(id);
        model.addAttribute("course", existingCouser);
        return "addCourse";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") int id, @ModelAttribute Course course, Model model) {
        course.setCourseId(id);

        var updatedCourse = courseServices.updateCourse(course);
        if (updatedCourse == null) {
            model.addAttribute("error", true);
            return "addCourse";
        }
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        courseServices.deleteCourse(id);
        return "redirect:/home";
    }
}
