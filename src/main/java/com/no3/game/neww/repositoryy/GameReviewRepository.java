package com.no3.game.neww.repositoryy;

import com.no3.game.entity.Member;
import com.no3.game.neww.entityy.GameReview;
import com.no3.game.neww.entityy.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameReviewRepository extends JpaRepository<GameReview, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<GameReview> findByMovie(Movie movie);


    @Modifying
    @Query("delete from Review mr where mr.member = :member")
    void deleteByMember(Member member);

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    Page<GameReview> findAll(Pageable pageable);

}
