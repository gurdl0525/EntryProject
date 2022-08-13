package com.example.assignment.domain.entity.likes;

import com.example.assignment.domain.entity.novel.Novel;
import com.example.assignment.domain.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends CrudRepository<Likes, Long> {

    boolean existsByNovelAndUser(Novel novel, User user);

    Optional<Likes> findByNovelAndUser(Novel novel, User user);

    List<Likes> findLikesByUser(User user);

    List<Likes> findLikesByNovel(Novel novel);

}
