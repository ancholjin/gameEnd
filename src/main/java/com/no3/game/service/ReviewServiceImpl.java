package com.no3.game.service;

import com.no3.game.dto.PageRequestDTO;
import com.no3.game.dto.PageResultDTO;
import com.no3.game.dto.ReviewDTO;
import com.no3.game.entity.Review;
import com.no3.game.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    @Override
    public Long register(ReviewDTO reviewDTO) {

        Review review = dtoToEntity(reviewDTO);

        reviewRepository.save(review);

        return review.getId();
    }



    @Override
    public ReviewDTO get(Long id) {
        Optional<Review> result = reviewRepository.findById(id);

        if(result.isPresent()){
            return entityToDTO(result.get());
        }
        return null;
    }


    @Override
    public void remove(Long id) {

        log.info("remove " + id);

        reviewRepository.deleteById(id);

    }

    @Override
    public Long modify(ReviewDTO reviewDTO) {

        Review review = dtoToEntity(reviewDTO);

        log.info("===================================");
        log.info(review);

        reviewRepository.save(review);

        return review.getId();
    }

    @Override
    public PageResultDTO<ReviewDTO, Review> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);
        Function<Review, ReviewDTO> fn = (en -> entityToDTO(en));
        Pageable pageRequest = pageRequestDTO.getPageable(Sort.by("id").descending());
        Page<Review> result = reviewRepository.getList((PageRequest) pageRequest);
        return new PageResultDTO<>(result, fn);

    }

}
