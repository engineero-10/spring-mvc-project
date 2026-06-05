package org.example.springmvcproject.repository;

import org.example.springmvcproject.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstuctorRepository extends JpaRepository<Instructor,Integer> {
    public Instructor findByEmailAndPassword(String email,String name);

    public Instructor findByEmail(String email);
}
