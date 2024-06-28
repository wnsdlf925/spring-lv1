package com.sparta.springlv1.board.dto;

import com.sparta.springlv1.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Builder
public record BoardResponseDto(
        Long id,
        String title,
        LocalDate createDate,
        String userName,
        String contents) {

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .userName(board.getUserName())
                .contents(board.getContents())
                .createDate(board.getCreateDate())
                .build();
    }

}
