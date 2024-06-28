package com.sparta.springlv1.board.dto;

import com.sparta.springlv1.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;


public record BoardRequestDto(
        Long id,
        String title,
        String userName,
        String password,
        String contents) {




}
