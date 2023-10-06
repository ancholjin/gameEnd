package com.no3.game.repository;

import com.no3.game.entity.Item;
import com.no3.game.entity.Member;
import com.no3.game.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepoTest {

    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    public void insertItemReviews() {

        //200개의 리뷰를 등록
        IntStream.rangeClosed(1,200).forEach(i -> {

            //아이템 아이디
            Long itemId = (long)(5);

            //맴버 아이디
            Long memberId  =  (long)(104);
            Member member = Member.builder().id(memberId).build();

            Review itemReview = Review.builder()
                    .member(member)
                    .item(Item.builder().id(itemId).build())
                    .grade((int)(Math.random()* 5) + 1)
                    .text("이 게임에 대한 느낌..."+i)
                    .build();

            reviewRepository.save(itemReview);
        });
    }


    @Test
    public void testGetItemReviews() {

        Item item = Item.builder().id(4L).build();

        List<Review> result = reviewRepository.findByItem(item);

        result.forEach(itemReview -> {

            System.out.print(itemReview.getId());
            System.out.print("\t"+itemReview.getGrade());
            System.out.print("\t"+itemReview.getText());
            System.out.print("\t"+itemReview.getMember().getEmail());
            System.out.println("---------------------------");
        });
    }
}
