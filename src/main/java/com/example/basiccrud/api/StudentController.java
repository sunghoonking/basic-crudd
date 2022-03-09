package com.example.basiccrud.api;

import com.example.basiccrud.domain.Professor;
import com.example.basiccrud.domain.Student;
import com.example.basiccrud.dto.StudentRequestDto;
import com.example.basiccrud.repository.StudentRepository;
import com.example.basiccrud.service.StudentService;
import com.example.basiccrud.utills.PagingResult;
import com.example.basiccrud.utills.Wrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class StudentController {

    private  final StudentRepository studentRepository;
    private  final StudentService studentService;

    // 학생 저장
    @PostMapping("/api/students")
    public void createStudentInfo(@RequestBody StudentRequestDto studentDto){
        studentService.setStudentInfo(studentDto);

    }

    // 학생 목록 조회
    @GetMapping("/api/students/{curPage}")
    public PagingResult readStudents(@PathVariable Integer curPage){
        return studentService.getStudents(curPage);


    }
    @RequestMapping(value = "/api/students",method=RequestMethod.GET)
    public List<Student> readStudents(){
        return studentRepository.findAll();

    }

    // 학생 목록 변경
    @PutMapping("/api/students/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto studentDto){
        return studentService.update(id,studentDto);
    }

    // 학생 목록 삭제
    @DeleteMapping("/api/students")
    public void deleteStudent(@RequestBody Wrapper students){
        studentService.deleteStudents(students);







    }




}
