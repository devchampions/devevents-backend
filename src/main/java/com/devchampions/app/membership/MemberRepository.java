package com.devchampions.app.membership;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends org.springframework.data.repository.Repository<Member, Long> {

    Optional<Member> findByUuid(String id);

    void save(Member member);

}
