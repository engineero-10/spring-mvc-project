package org.example.springmvcproject.controller;

import org.example.springmvcproject.entity.Student;
import org.example.springmvcproject.services.StudentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("students")
public class StudentController {
    private final StudentServices studentServices;
    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }
    @GetMapping("/add")
    public String loadStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student, Model model) {
        var newStudent = studentServices.addStudent(student);
        if (newStudent == null) {
            model.addAttribute("error", true);
            return "addStudent";
        }
        return "redirect:/home";
    }
}
