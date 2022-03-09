package com.example.basiccrud.domain;

import com.example.basiccrud.dto.StudentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Student extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "student_id")
    private Long id;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private int studentAge;

    @Column(nullable = false)
    private String studentAddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "subjectId",nullable = true)
    private Subject subject;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "professorId",nullable = true)
    private Professor professor;





    public Student(StudentRequestDto studentDto, Subject s, Professor p){
        this.studentName = studentDto.getStudentName();
        this.studentAge = studentDto.getStudentAge();
        this.studentAddress = studentDto.getStudentAddress();
        this.subject = s;
        this.professor = p;


    }

    public Student update(StudentRequestDto studentRequestDto){
        this.studentName = studentRequestDto.getStudentName();
        this.studentAge = studentRequestDto.getStudentAge();

        return this;
    }

}
