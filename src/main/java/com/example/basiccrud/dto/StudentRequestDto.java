package com.example.basiccrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDto {


    private String studentName;
    private int studentAge;
    private String studentAddress;
    private String subjectName;
    private String professorName;

}

