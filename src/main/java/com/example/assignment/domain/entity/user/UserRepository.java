package com.example.assignment.domain.entity.user;

import com.example.assignment.domain.entity.novel.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccountId(String accountId);

    Optional<User> findByNickname(String nickname);

}
