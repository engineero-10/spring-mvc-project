package org.example.springmvcproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
    @ManyToOne()
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
