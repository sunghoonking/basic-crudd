package com.example.basiccrud.repository;


import com.example.basiccrud.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    Page<Student> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
