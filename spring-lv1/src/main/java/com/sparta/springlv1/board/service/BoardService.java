package com.sparta.springlv1.board.service;

import com.sparta.springlv1.board.dto.BoardRequestDto;
import com.sparta.springlv1.board.dto.BoardResponseDto;
import com.sparta.springlv1.entity.Board;

import java.util.List;

public interface BoardService {

    BoardResponseDto postBoard(BoardRequestDto boardRequestDto);

    List<BoardResponseDto> getBoardlist();

    BoardResponseDto getBoard(Long id);

    BoardResponseDto updateBoard(BoardRequestDto boardRequestDto);

    String deleteBoard(BoardRequestDto boardRequestDto);
}
