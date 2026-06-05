package org.example.springmvcproject.services;

import org.example.springmvcproject.entity.Enrollment;
import org.example.springmvcproject.repository.EnrollmentRepository;
import org.example.springmvcproject.scopes.SessionInstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentServices {
    private final EnrollmentRepository enrollmentRepository;
    private final SessionInstructor sessionInstructor;

    public EnrollmentServices(EnrollmentRepository enrollmentRepository, SessionInstructor sessionInstructor) {
        this.enrollmentRepository = enrollmentRepository;
        this.sessionInstructor = sessionInstructor;
    }

    public List<Enrollment> getAllEnrollments() {
        var instructor = sessionInstructor.getInstructor();
        if (instructor == null) {
            return List.of();
        }
        return enrollmentRepository.findAllByCourse_Instructor_Id(instructor.getId());
    }

    public boolean checkDuplicateEnrollment(int courseId, int stdId) {
        var enrollments = enrollmentRepository.findByCourse_CourseIdAndAndStudent_Id(courseId, stdId);
        return enrollments != null;
    }

    @Transactional
    public void deleteEnrollment(int id) {
        var instructor = sessionInstructor.getInstructor();
        if (instructor == null) {
            return;
        }
        enrollmentRepository.deleteByIdAndCourse_Instructor_Id(id, instructor.getId());
    }

    public void updateEnrollment(Enrollment enrollment) {
        var existing = getEnrollmentById(enrollment.getId());
        if (existing == null) {
            return;
        }
        enrollmentRepository.save(enrollment);
    }

    public void saveEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public Enrollment getEnrollmentById(int id) {
        var instructor = sessionInstructor.getInstructor();
        if (instructor == null) {
            return null;
        }
        return enrollmentRepository.findByIdAndCourse_Instructor_Id(id, instructor.getId());
    }

    public boolean checkDuplicateEnrollmentForUpdate(
            int studentId,
            int courseId,
            int enrollmentId) {

        return enrollmentRepository
                .existsByStudent_IdAndCourse_CourseIdAndIdNot(
                        studentId,
                        courseId,
                        enrollmentId
                );
    }
}
