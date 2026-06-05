package org.example.springmvcproject.services;

import org.example.springmvcproject.entity.Enrollment;
import org.example.springmvcproject.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServices {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServices(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public boolean checkDuplicateEnrollment(int courseId, int stdId) {
        var enrollments = enrollmentRepository.findByCourse_CourseIdAndAndStudent_Id(courseId, stdId);
        return enrollments != null;
    }

    public void deleteEnrollment(int id) {
        enrollmentRepository.deleteById(id);
    }

    public void updateEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public void saveEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public Enrollment getEnrollmentById(int id) {
        return enrollmentRepository.findById(id).orElse(null);
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
