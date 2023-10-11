package com.no3.game.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long id;

    private String title;

    private String text;

    private int grade;

    private String itemTitle; // 아이템 이름
    private String writerEmail; // 작성자의 이메일
    private String writerName; // 작성자의 이름

    private LocalDateTime regDate,modDate;

    private ArrayList<ItemImgDTO> img;


}
