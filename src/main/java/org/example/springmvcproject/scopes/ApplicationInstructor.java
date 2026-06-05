package org.example.springmvcproject.scopes;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class ApplicationInstructor {
    private int registeredNumber;

    public int getRegisteredNumber() {
        return registeredNumber;
    }

    public void increaseRegisteredNumber() {
        registeredNumber++;
    }

    public void decreaseRegisteredNumber() {
        registeredNumber--;
    }
}
