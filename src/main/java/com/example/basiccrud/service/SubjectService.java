package com.example.basiccrud.service;

import com.example.basiccrud.domain.Subject;
import com.example.basiccrud.dto.SubjectRequestDto;
import com.example.basiccrud.repository.SubjectRepository;
import com.example.basiccrud.utills.PagingResult;
import com.example.basiccrud.utills.Wrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubjectService {

    private  final SubjectRepository subjectRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 8;

    //과목 저장 및 중복 저장 x
    @Transactional
    public String setSubject(SubjectRequestDto subjectRequestDto){

        String sub = subjectRequestDto.getSubjectName();
        Optional<Subject> found = subjectRepository.findBySubjectName(sub);
        if(found.isPresent()){
            throw new IllegalArgumentException("이미있는 학과명 입니다");

        } else {
            Subject subject = new Subject(sub);
            subjectRepository.save(subject);

        }
        return "false";
    }

    //과목 조회 페이징
    public PagingResult getSubject(int curPage){
        Pageable pageable = PageRequest.of(curPage-1,BLOCK_PAGE_NUM_COUNT);
        Page<Subject> subjects = subjectRepository.findAllByOrderByCreatedAtDesc(pageable);

        List<Subject> subjectList = subjects.getContent();
        System.out.println(subjectList);
        return new PagingResult(subjectList, subjects.getTotalPages());


    }
    //과목 변경
    @Transactional
    public String updateSubject(Long id, SubjectRequestDto subjectRequestDto){
        Subject subject = subjectRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다")
        );
        subject.update(subjectRequestDto);
        return "success";
    }
    
    //과목 삭제
    @Transactional
    public void deleteSubject(Wrapper subjects){
        List<String> test = subjects.getSubjects();
        for(String s: test){
            long id = Long.parseLong(s);
            subjectRepository.deleteById(id);
        }
    }






}
