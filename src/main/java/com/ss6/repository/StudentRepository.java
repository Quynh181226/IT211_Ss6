package com.ss6.repository;

import com.ss6.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Create by Quynh at 2:30 CH on 20/05/2026
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
