package com.sparta.springlv1.board.controller;


import com.sparta.springlv1.board.dto.BoardRequestDto;
import com.sparta.springlv1.board.dto.BoardResponseDto;
import com.sparta.springlv1.board.service.BoardService;
import com.sparta.springlv1.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BaordController {

    private final BoardService boardService;

    @GetMapping("/list")
    public List<BoardResponseDto> getBoardList(){
        return boardService.getBoardlist();
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id){

        return boardService.getBoard(id);
    }

    @PostMapping
    public BoardResponseDto postBoard(@RequestBody BoardRequestDto boardRequestDto){
        return boardService.postBoard(boardRequestDto);
    }

    @PutMapping
    public BoardResponseDto updateBoard(@RequestBody BoardRequestDto boardRequestDto){
        return boardService.updateBoard(boardRequestDto);
    }

    @DeleteMapping
    public String deleteBoard(@RequestBody BoardRequestDto boardRequestDto){
        return boardService.deleteBoard(boardRequestDto);
    }
}
