package com.sparta.springlv1.entity;


import com.sparta.springlv1.board.dto.BoardRequestDto;
import com.sparta.springlv1.board.dto.BoardResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@NoArgsConstructor
@SuperBuilder
@Data
@Table(name = "board")
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 200)
    private String contents;

    @Column(length = 50)
    private String userName;

    @Column(length = 50)
    private String password;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate createDate;



    public static Board from(BoardRequestDto boardRequestDto) {
        return Board.builder()
                .id(boardRequestDto.id())
                .title(boardRequestDto.title())
                .userName(boardRequestDto.userName())
                .password(boardRequestDto.password())
                .contents(boardRequestDto.contents())
                .build();
    }


}
