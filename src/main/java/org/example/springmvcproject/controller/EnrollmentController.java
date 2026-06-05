package org.example.springmvcproject.controller;

import org.example.springmvcproject.entity.Enrollment;
import org.example.springmvcproject.services.CourseServices;
import org.example.springmvcproject.services.EnrollmentServices;
import org.example.springmvcproject.services.StudentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final StudentServices studentServices;
    private final CourseServices courseServices;
    private final EnrollmentServices enrollmentServices;

    public EnrollmentController(StudentServices studentServices,
                                CourseServices courseServices,
                                EnrollmentServices enrollmentServices) {
        this.studentServices = studentServices;
        this.courseServices = courseServices;
        this.enrollmentServices = enrollmentServices;
    }

    @GetMapping
    public String enrollment(Model model) {
        model.addAttribute("enrollments", enrollmentServices.getAllEnrollments());
        return "enrollment";
    }

    @GetMapping("/add")
    public String showEnrollmentForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("studentsList", studentServices.getAllStudents());
        model.addAttribute("coursesList", courseServices.getAllCourses());
        return "assing-student-in-course";
    }

    @PostMapping("/add")
    public String registerStudentInCourse(@ModelAttribute("enrollment") Enrollment enrollment, Model model) {
        enrollment.setEnrollmentDate(LocalDate.now());
        boolean isAlreadyEnrolled = enrollmentServices.checkDuplicateEnrollment(
                enrollment.getCourse().getCourseId(),
                enrollment.getStudent().getId()
        );

        if (isAlreadyEnrolled) {
            model.addAttribute("error", "This student is already enrolled in this course!");
            model.addAttribute("studentsList", studentServices.getAllStudents());
            model.addAttribute("coursesList", courseServices.getAllCourses());
            return "assing-student-in-course";
        }
        enrollmentServices.saveEnrollment(enrollment);
        return "redirect:/enrollment";
    }

    @GetMapping("/update/{id}")
    public String showUpdateEnrollmentForm(@PathVariable("id") int id, Model model) {
        Enrollment existingEnrollment = enrollmentServices.getEnrollmentById(id);

        model.addAttribute("enrollment", existingEnrollment);
        model.addAttribute("studentsList", studentServices.getAllStudents());
        model.addAttribute("coursesList", courseServices.getAllCourses());

        return "assing-student-in-course";
    }

    @PostMapping("/update/{id}")
    public String updateEnrollment(
            @PathVariable("id") int id,
            @ModelAttribute("enrollment") Enrollment enrollment,
            Model model) {

        enrollment.setId(id);

        boolean isAlreadyEnrolled =
                enrollmentServices.checkDuplicateEnrollmentForUpdate(
                        enrollment.getStudent().getId(),
                        enrollment.getCourse().getCourseId(),
                        id
                );

        if (isAlreadyEnrolled) {
            model.addAttribute("error",
                    "This student is already enrolled in this course!");

            model.addAttribute("studentsList",
                    studentServices.getAllStudents());

            model.addAttribute("coursesList",
                    courseServices.getAllCourses());

            return "assing-student-in-course";
        }

        Enrollment original =
                enrollmentServices.getEnrollmentById(id);

        if (original != null) {
            enrollment.setEnrollmentDate(
                    original.getEnrollmentDate());
        } else {
            enrollment.setEnrollmentDate(LocalDate.now());
        }

        enrollmentServices.updateEnrollment(enrollment);

        return "redirect:/enrollment";
    }

    @GetMapping("/delete/{id}")
    public String deleteEnrollment(@PathVariable int id) {
        enrollmentServices.deleteEnrollment(id);
        return "redirect:/enrollment";
    }
}