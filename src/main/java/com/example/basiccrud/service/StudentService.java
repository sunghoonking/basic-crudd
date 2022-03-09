package com.example.basiccrud.service;


import com.example.basiccrud.domain.Professor;
import com.example.basiccrud.domain.Student;
import com.example.basiccrud.domain.Subject;
import com.example.basiccrud.dto.StudentRequestDto;
import com.example.basiccrud.repository.ProfessorRepository;
import com.example.basiccrud.repository.StudentRepository;
import com.example.basiccrud.repository.SubjectRepository;
import com.example.basiccrud.utills.PagingResult;
import com.example.basiccrud.utills.Wrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5;

    @Transactional
    public void setStudentInfo(StudentRequestDto studentDto){

        Subject s = subjectRepository.findBySubjectName(studentDto.getSubjectName()).orElseThrow(
                ()-> new NullPointerException("과목이 없습니다")
        );

        Professor p = professorRepository.findByProfessorName(studentDto.getProfessorName()).orElseThrow(
                ()-> new NullPointerException("교수가 없습니다")
        );

        Student student = new Student(studentDto,s,p);

        studentRepository.save(student);

    }

    // 페이징
    public PagingResult getStudents(int curPage){
        Pageable pageable = PageRequest.of(curPage-1, BLOCK_PAGE_NUM_COUNT);
        Page<Student> students = studentRepository.findAllByOrderByCreatedAtDesc(pageable);

        List<Student> studentList = students.getContent();
        return new PagingResult(studentList, students.getTotalPages());
    }


    @Transactional
    public String update(Long id, StudentRequestDto studentDto){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다")
        );
        student.update(studentDto);

        return "success";
    }

    @Transactional
    public void deleteStudents(Wrapper students){
        List<String> test = students.getStudents();
        System.out.println(test);
        for (String s : test
        ) {
            long id = Long.parseLong(s);

            studentRepository.deleteById(id);
        }


    }


}
