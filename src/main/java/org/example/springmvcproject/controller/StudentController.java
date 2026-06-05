package org.example.springmvcproject.controller;

import org.example.springmvcproject.entity.Student;
import org.example.springmvcproject.services.StudentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addStudent(@ModelAttribute Student student,
                             Model model) {
        var student1 = studentServices.addStudent(student);
        if (student1 == null) {
            model.addAttribute("error", true);
            return "addStudent";
        }
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Student existingStudent = studentServices.getStudentById(id);
        if (existingStudent == null) {
            return "redirect:/home";
        }
        model.addAttribute("student", existingStudent);
        return "addStudent";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") int id, @ModelAttribute Student student, Model model) {
        student.setId(id);

        var updatedStudent = studentServices.updateStudent(student);
        if (updatedStudent == null) {
            model.addAttribute("error", true);
            return "addStudent";
        }
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        studentServices.deleteStudent(id);
        return "redirect:/home";
    }
}