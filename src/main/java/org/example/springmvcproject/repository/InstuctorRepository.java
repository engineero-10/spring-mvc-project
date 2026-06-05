package org.example.springmvcproject.repository;

import org.example.springmvcproject.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstuctorRepository extends JpaRepository<Instructor, Integer> {
    Instructor findByEmailAndPassword(String email, String name);

    Instructor findByEmail(String email);
}
