package com.no3.game.repository;

import com.no3.game.entity.Item;
import com.no3.game.entity.Member;
import com.no3.game.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> ,
        QuerydslPredicateExecutor<Review> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByItem(Item item);

    @Modifying
    @Query("delete from Review mr where mr.member = :member")
    void deleteByMember(Member member);


    @EntityGraph(attributePaths = "imgs" , type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT i FROM Review i ")
    Page<Review> getList(PageRequest pageRequest);

}
