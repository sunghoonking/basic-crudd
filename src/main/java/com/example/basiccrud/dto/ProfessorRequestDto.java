package com.example.basiccrud.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorRequestDto {

    private String professorName;
    private int professorAge;
    private String subjectName;
}
