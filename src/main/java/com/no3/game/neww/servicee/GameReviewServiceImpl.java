package com.no3.game.neww.servicee;

import com.no3.game.neww.dtoo.ReviewDTO;
import com.no3.game.neww.entityy.GameReview;
import com.no3.game.neww.entityy.Movie;

import com.no3.game.neww.repositoryy.GameReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class GameReviewServiceImpl implements GameReviewService {

    private final GameReviewRepository reviewRepository;


    @Override
    public List<ReviewDTO> getListOfMovie(Long mno){

        Movie movie = Movie.builder().mno(mno).build();

        List<GameReview> result = reviewRepository.findByMovie(movie);

        return result.stream().map(movieReview -> entityToDto(movieReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO movieReviewDTO) {

        GameReview movieReview = dtoToEntity(movieReviewDTO);

        reviewRepository.save(movieReview);

        return movieReview.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO movieReviewDTO) {

        Optional<GameReview> result =
                reviewRepository.findById(movieReviewDTO.getReviewnum());

        if(result.isPresent()){

            GameReview movieReview = result.get();
            movieReview.changeGrade(movieReviewDTO.getGrade());
            movieReview.changeText(movieReviewDTO.getText());

            reviewRepository.save(movieReview);
        }

    }

    @Override
    public void remove(Long reviewnum) {

        reviewRepository.deleteById(reviewnum);

    }
}

