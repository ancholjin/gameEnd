package com.no3.game.repository;

import com.no3.game.constant.Role;
import com.no3.game.entity.Member;
import groovy.util.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;


public class MemberRepoTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;


/*
    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1,5).forEach(i -> {
            Member member = Member.builder()
                    .email("a"+i +"@naver.com")
                    .password("1234")
                    .name("test"+i)
                    .role(Role.USER)
                    .build();
            memberRepository.save(member);
        }); // USER insert test

    }
*/


    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {

        Long member_id = 1L; // Member의 id

        Member member = Member.builder().id(member_id).build();

        //기존
        //memberRepository.deleteById(mid);
        //reviewRepository.deleteByMember(member);

        //순서 주의
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(member_id);

    }
}
