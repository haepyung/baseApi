package com.base.apiserver.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.apiserver.model.Member;


public interface MemberRepo extends JpaRepository<Member, String> {
    List<Member> findAll();
    Optional<Member> findByUsername(String username);
    Optional<Member> findById(Long id);
    Integer countByUsername(String username);
    Member save(Member account);
}
