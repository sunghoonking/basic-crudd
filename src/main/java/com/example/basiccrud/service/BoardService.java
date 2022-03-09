package com.example.basiccrud.service;


import com.example.basiccrud.domain.Board;
import com.example.basiccrud.domain.User;
import com.example.basiccrud.dto.BoardRequestDto;
import com.example.basiccrud.repository.BoardRepository;
import com.example.basiccrud.utills.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 10;


    // 게시판 글 생성
    @Transactional
    public Board setBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = new Board(boardRequestDto, user);
        boardRepository.save(board);
        return board;

    }


    // 게시판 글 조회
    public PagingResult getBoards(int curPage) {
        Pageable pageable = PageRequest.of(curPage-1, BLOCK_PAGE_NUM_COUNT);
        Page<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc(pageable);

        List<Board> boardList = boards.getContent();

        return new PagingResult(boardList, boards.getTotalPages());
    }

    // 게시판 수정
    @Transactional
    public String updateBoard(Long id, BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지않습니다.")
        );

        if(user.getUsername().equals(board.getUser().getUsername())){
            board.update(boardRequestDto, user);
            return "success";
        }else{
            return "fail";
        }

    }

    // 게시판 삭제
    public String deleteById(Long id, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지않습니다.")
        );

        if(user.getUsername().equals(board.getUser().getUsername())){
            boardRepository.delete(board);
            return "success";
        }else{
            return "fail";
        }
    }

}
