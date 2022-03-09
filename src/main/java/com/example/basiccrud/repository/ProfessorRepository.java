package com.example.basiccrud.repository;


import com.example.basiccrud.domain.Professor;
import com.example.basiccrud.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Page<Professor> findAllByOrderByCreatedAtDesc(Pageable pageable);


    Optional<Professor> findByProfessorName(String professorName);


}
