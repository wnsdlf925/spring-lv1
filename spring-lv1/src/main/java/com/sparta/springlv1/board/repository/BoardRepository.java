package com.sparta.springlv1.board.repository;

import com.sparta.springlv1.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findFirstByTitleAndPasswordAndUserNameOrderByCreateDateDesc(String title, String password, String userName);
    List<Board> findAllByOOrderByCreateDateDesc();
}
