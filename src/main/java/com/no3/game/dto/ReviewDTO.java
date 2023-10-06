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

    private LocalDateTime regDate,modDate;

    private ArrayList<ItemImgDTO> img;




}
