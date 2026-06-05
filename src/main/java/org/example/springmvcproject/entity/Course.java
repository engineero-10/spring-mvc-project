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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"courseName"}
                )
        }
)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;

    @ManyToOne()
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
    private String courseName;
    private String description;
    private float price;
    private int durationHourse;
}
