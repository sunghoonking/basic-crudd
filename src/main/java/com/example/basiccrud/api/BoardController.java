package com.example.basiccrud.api;


import com.example.basiccrud.domain.User;
import com.example.basiccrud.dto.BoardRequestDto;
import com.example.basiccrud.security.UserDetailsImpl;
import com.example.basiccrud.service.BoardService;
import com.example.basiccrud.utills.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private BoardService boardService;


    //게시판 글 생성
    @PostMapping("/api/boards")
    public String createBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        System.out.println(boardRequestDto);

        String user1 = user.toString();
        System.out.println(user1);
        boardService.setBoard(boardRequestDto, user);
        return "ok";
    }
    //게시한 글 조회
    @GetMapping("/api/boards/{curPage}")
    public PagingResult readBoard(@PathVariable Integer curPage) {
        return boardService.getBoards(curPage);
    }

    // 게시판 글 수정
    @PutMapping("/api/boards/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return boardService.updateBoard(id, requestDto, user);
    }


    // 게시판 글 삭제
    //작성글 삭제
    @DeleteMapping("/api/boards/{id}")
    public String deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return boardService.deleteById(id, user);
    }




}
