package com.example.basiccrud.dto.responseDto;


import com.example.basiccrud.domain.Professor;
import com.example.basiccrud.domain.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorResponseDto {

    private String professorName;
    private int professorAge;
    private String subjectName;


    public ProfessorResponseDto(String professorName, int professorAge, String subjectName){
        this.professorName = professorName;
        this.professorAge = professorAge;
        this.subjectName = subjectName;
    }
}