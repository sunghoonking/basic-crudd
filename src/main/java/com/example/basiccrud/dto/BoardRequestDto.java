package com.example.basiccrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BoardRequestDto {
    private Long id;
    private String title;
    private String content;
}
