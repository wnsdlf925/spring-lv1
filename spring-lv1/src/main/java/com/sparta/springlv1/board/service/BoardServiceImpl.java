package com.sparta.springlv1.board.service;

import com.sparta.springlv1.board.dto.BoardRequestDto;
import com.sparta.springlv1.board.dto.BoardResponseDto;
import com.sparta.springlv1.board.repository.BoardRepository;
import com.sparta.springlv1.entity.Board;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public BoardResponseDto postBoard(BoardRequestDto boardRequestDto) {


        Board board = boardRequestDto.from(boardRequestDto);
        try {
            boardRepository.save(board);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return boardRepository
                .findFirstByTitleAndPasswordAndUserNameOrderByCreateDateDesc(board.getTitle(),board.getPassword(),board.getUserName())
                .map(BoardResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("error"));
    }

    @Override
    public List<BoardResponseDto> getBoardlist() {

        List<BoardResponseDto> list = boardRepository.findAllByOOrderByCreateDateDesc().stream()
                .map(BoardResponseDto::from)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public BoardResponseDto getBoard(Long id) {

        return boardRepository.findById(id)
                .map(BoardResponseDto::from)
                .orElseThrow(()->
                new IllegalArgumentException("error"));


    }

    @Override
    public BoardResponseDto updateBoard(BoardRequestDto boardRequestDto) {

        Board board = boardRepository.findById(boardRequestDto.id())
                .orElseThrow(()->
                        new IllegalArgumentException("error"));

        if(!boardRequestDto.password().equals(board.getPassword())){
            throw new IllegalArgumentException("error");
        }

        board.setContents(boardRequestDto.contents());
        board.setTitle(boardRequestDto.title());
        board.setUserName(boardRequestDto.userName());

        boardRepository.save(board);

        return BoardResponseDto.from(board);
    }

    @Override
    public String deleteBoard(BoardRequestDto boardRequestDto) {

        Board board = boardRepository.findById(boardRequestDto.id())
                .orElseThrow(()->
                        new IllegalArgumentException("error"));

        if(!boardRequestDto.password().equals(board.getPassword())){
            throw new IllegalArgumentException("error");
        }

        boardRepository.delete(board);


        return "success";
    }
}
