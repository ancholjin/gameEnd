package com.no3.game.neww.repositoryy;


import com.no3.game.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameMemberRepository extends JpaRepository<Member, Long> {

}
