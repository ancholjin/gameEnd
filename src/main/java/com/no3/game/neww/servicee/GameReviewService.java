package com.no3.game.neww.servicee;


import com.no3.game.entity.Member;
import com.no3.game.neww.dtoo.ReviewDTO;
import com.no3.game.neww.entityy.GameReview;
import com.no3.game.neww.entityy.Movie;


import java.util.List;


public interface GameReviewService {

    //영화의 모든 영화리뷰를 가져온다.
    List<ReviewDTO> getListOfMovie(Long mno);

    //영화 리뷰를 추가
    Long register(ReviewDTO movieReviewDTO);

    //특정한 영화리뷰 수정
    void modify(ReviewDTO movieReviewDTO);

    //영화 리뷰 삭제
    void remove(Long reviewnum);

    default GameReview dtoToEntity(ReviewDTO movieReviewDTO){

        GameReview movieReview = GameReview.builder()
                .reviewnum(movieReviewDTO.getReviewnum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(Member.builder().id(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();

        return movieReview;
    }

    default ReviewDTO entityToDto(GameReview movieReview){

        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                .reviewnum(movieReview.getReviewnum())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getId())
                .nickname(movieReview.getMember().getName())
                .email(movieReview.getMember().getEmail())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegTime())
                .modDate(movieReview.getUpdateTime())
                .build();

        return movieReviewDTO;
    }
}
