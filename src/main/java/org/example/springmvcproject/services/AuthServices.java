package org.example.springmvcproject.services;

import org.example.springmvcproject.entity.Instructor;
import org.example.springmvcproject.repository.InstuctorRepository;
import org.example.springmvcproject.scopes.ApplicationInstructor;
import org.example.springmvcproject.scopes.SessionInstructor;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    private final ApplicationInstructor applicationInstructor;
    private final SessionInstructor sessionInstructor;
    private final InstuctorRepository instructorRepository;

    public AuthServices(ApplicationInstructor applicationInstructor, SessionInstructor sessionInstructor, InstuctorRepository instructorRepository) {
        this.applicationInstructor = applicationInstructor;
        this.sessionInstructor = sessionInstructor;
        this.instructorRepository = instructorRepository;
    }

    // login logic
    public boolean login(String email, String password) {
        var instructor = instructorRepository.findByEmailAndPassword(email, password);
        if (instructor == null) {
            return false;
        }
        sessionInstructor.setInstructor(instructor);
        applicationInstructor.increaseRegisteredNumber();////////////////////
        return true;
    }

    // register logic
    public boolean register(Instructor instructor) {
        var instructor1 = instructorRepository.findByEmail(instructor.getEmail());
        if (instructor1 != null) {
            return false;
        }
        instructorRepository.save(instructor);
//        applicationInstructor.increaseRegisteredNumber();
        return true;
    }

    public void logout() {
        sessionInstructor.setInstructor(null);
        applicationInstructor.decreaseRegisteredNumber();
    }
}
