package com.sparta.springlv1.board.controller;


import com.sparta.springlv1.board.dto.BoardRequestDto;
import com.sparta.springlv1.board.dto.BoardResponseDto;
import com.sparta.springlv1.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity getBoardList(){
        return ResponseEntity
                .ok()
                .body(boardService.getBoardlist());
    }

    @GetMapping("/{id}")
    public ResponseEntity getBoard(@PathVariable Long id){

        return ResponseEntity
                .ok()
                .body(boardService.getBoard(id));
    }

    @PostMapping
    public ResponseEntity postBoard(@RequestBody BoardRequestDto boardRequestDto){
        return ResponseEntity
                .ok()
                .body(boardService.postBoard(boardRequestDto));
    }

    @PutMapping
    public ResponseEntity updateBoard(@RequestBody BoardRequestDto boardRequestDto){
        return ResponseEntity
                .ok()
                .body(boardService.updateBoard(boardRequestDto));
    }

    @DeleteMapping
    public ResponseEntity deleteBoard(@RequestBody BoardRequestDto boardRequestDto){

        return ResponseEntity
                .ok()
                .body(boardService.deleteBoard(boardRequestDto));
    }
}
