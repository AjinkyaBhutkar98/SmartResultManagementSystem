package com.ajinkyabhutkar.smartresult.repo;

import com.ajinkyabhutkar.smartresult.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {

    Student findByRollNo(String rollNo);
}
