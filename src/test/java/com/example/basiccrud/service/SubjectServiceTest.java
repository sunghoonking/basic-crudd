package com.example.basiccrud.service;


import com.example.basiccrud.domain.Subject;
import com.example.basiccrud.dto.SubjectRequestDto;
import com.example.basiccrud.repository.SubjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Transactional
@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {


    @Mock
    SubjectRepository subjectRepository;

    SubjectRequestDto subjectRequestDto;

    @InjectMocks
    SubjectService subjectService;

    @Test
    @DisplayName("과목을 처음 저장할 때")
    void newSubject(){

        //given
        String subjectName = "컴퓨터공학과";

        SubjectRequestDto subjectRequestDto = new SubjectRequestDto();
        subjectRequestDto.setSubjectName(subjectName);

        ArgumentCaptor<Subject> captor = ArgumentCaptor.forClass(Subject.class);

        given(subjectRepository.findBySubjectName("컴퓨터공학과")).willReturn(Optional.ofNullable(null));

        //when
        String resultCreateSubject = subjectService.setSubject(subjectRequestDto);

        //then
        verify(subjectRepository,times(1))
                .save(captor.capture());
        Subject result = captor.getValue();

        assertEquals(result.getSubjectName(),"컴퓨터공학과");


    }
    @Test
    @DisplayName("중복 과목을 저장할 때")
    void sameSubject(){
        //given
        String subject1 = "컴퓨터공학과";
        SubjectRequestDto subjectRequestDto = new SubjectRequestDto();
        subjectRequestDto.setSubjectName(subject1);

        Subject subject2 = new Subject();

        given(subjectRepository.findBySubjectName(subjectRequestDto.getSubjectName())).willReturn(Optional.of(subject2));
        //findBySubjectName 메소드에 subjectRequestDto.getSubjectName()) 값을 넘기면 Optional.of(subject2)객체를 리턴하도록 stubbing 한 것.

        //when
        String resultCheckSubject = subjectService.setSubject(subjectRequestDto);
        System.out.println(resultCheckSubject);

        //then
        assertEquals(resultCheckSubject, "이미있는 학과명 입니다");

    }
}




