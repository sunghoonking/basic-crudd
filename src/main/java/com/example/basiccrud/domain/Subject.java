package com.example.basiccrud.domain;


import com.example.basiccrud.dto.SubjectRequestDto;
import com.example.basiccrud.utills.SubjectValidator;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Subject extends TimeStamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "subject_id")
    private Long id;

    @Column(nullable = false)
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    List<Professor> professors = new ArrayList<>();

    public Subject(SubjectRequestDto subjectRequestDto){
        this.subjectName = subjectRequestDto.getSubjectName();
    }

    public Subject(String subjectName){
        SubjectValidator.validateCreateSubject(subjectName);
        this.subjectName = subjectName;
    }

    public Subject update(SubjectRequestDto  subjectRequestDto){
        this.subjectName = subjectRequestDto.getSubjectName();
        return this;
    }



}

