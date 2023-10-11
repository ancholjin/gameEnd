package com.no3.game.service;

import com.no3.game.dto.ItemImgDTO;
import com.no3.game.dto.PageRequestDTO;
import com.no3.game.dto.PageResultDTO;
import com.no3.game.dto.ReviewDTO;
import com.no3.game.entity.Item;
import com.no3.game.entity.ItemImg;
import com.no3.game.entity.Member;
import com.no3.game.entity.Review;

import java.util.ArrayList;
import java.util.HashSet;

public interface ReviewService {

    Long register(ReviewDTO reviewDTO);

    ReviewDTO get(Long id);

    PageResultDTO<ReviewDTO, Review> getList(PageRequestDTO pageRequestDTO);

    void remove(Long id);

    Long modify(ReviewDTO reviewDTO);

    default Review dtoToEntity(ReviewDTO reviewDTO){

        Item item = Item.builder()
                .title(reviewDTO.getItemTitle())
                .build();
        Member member = Member.builder()
                .email(reviewDTO.getWriterEmail())
                .build();

        Review review = Review.builder()
                .id(reviewDTO.getId())
                .text(reviewDTO.getText())
                .grade(reviewDTO.getGrade())
                .item(item)
                .member(member)
                .imgs(new HashSet<>())
                .build();

        ArrayList<ItemImgDTO> itemImgDTOList = reviewDTO.getImg();

        if(itemImgDTOList != null && itemImgDTOList.size() > 0){

            for (ItemImgDTO itemImgDTO : itemImgDTOList) {
                ItemImg itemImg = ItemImg.builder()
                        .id(itemImgDTO.getId())
                        .imgName(itemImgDTO.getImgName())
                        .uuid(itemImgDTO.getUuid())
                        .review(review)
                        .path(itemImgDTO.getPath()).build();

                review.addPhoto(itemImg);
            }
        }
        return review;
    }

    default ReviewDTO entityToDTO(Review review) {

        ReviewDTO dto = ReviewDTO.builder()
                .id(review.getId())
                .text(review.getText())
                .grade(review.getGrade())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();

        if (review.getImgs() != null && review.getImgs().size() > 0) {

            ArrayList<ItemImgDTO> itemImgDTOArrayList = new ArrayList<>();

            review.getImgs().stream().sorted((p1, p2) -> p1.getId() > p2.getId() ? -1 : 1)
                    .forEach(photo -> {

                        itemImgDTOArrayList.add(
                                ItemImgDTO.builder().id(photo.getId())
                                        .imgName(photo.getImgName())
                                        .uuid(photo.getUuid())
                                        .path(photo.getPath())
                                        .build());
                    });
            dto.setImg(itemImgDTOArrayList);
        }
        return dto;
    }

    default ReviewDTO entityToDTO(Review review, Member member, Item item) {

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .id(review.getId())
                .itemTitle(item.getTitle())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .build();

        return reviewDTO;

    }


}
