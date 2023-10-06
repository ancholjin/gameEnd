package com.no3.game.repository;

import com.no3.game.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.util.stream.IntStream;

public class MemberRepoTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;


/*    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder()
                    .email("r"+i +"@zerock.org")
                    .password("1111")
                    .name("reviewer"+i).build();
            memberRepository.save(member);
        });
    }*/


    @Test
    public void testDeleteMember() {//안됨

        String  memberEmail = "dsa@dsa"; //Member의 email

        Member member = Member.builder().email(memberEmail).build();

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(memberEmail);



    }
}
