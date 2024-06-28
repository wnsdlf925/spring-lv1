package com.sparta.springlv1.board.service;

import com.sparta.springlv1.board.dto.BoardRequestDto;
import com.sparta.springlv1.board.dto.BoardResponseDto;
import com.sparta.springlv1.board.repository.BoardRepository;
import com.sparta.springlv1.entity.Board;
import com.sparta.springlv1.global.CommonResponseDto;
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


        Board board = Board.from(boardRequestDto);
        try {
            boardRepository.save(board);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return boardRepository
                .findFirstByTitleAndPasswordAndUserNameOrderByCreateDateDesc(board.getTitle(),board.getPassword(),board.getUserName())
                .map(BoardResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("생성 실패"));
    }

    @Override
    public List<BoardResponseDto> getBoardlist() {

        List<BoardResponseDto> list = boardRepository.findAllByOrderByIdDesc()
                .stream()
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
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        board.setContents(boardRequestDto.contents());
        board.setTitle(boardRequestDto.title());
        board.setUserName(boardRequestDto.userName());

        boardRepository.save(board);

        return BoardResponseDto.from(board);
    }

    @Override
    public CommonResponseDto deleteBoard(BoardRequestDto boardRequestDto) {

        Board board = boardRepository.findById(boardRequestDto.id())
                .orElseThrow(()->
                        new IllegalArgumentException("삭제된 게시물"));

        if(!boardRequestDto.password().equals(board.getPassword())){
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        boardRepository.delete(board);


        return new CommonResponseDto("success");
    }
}
