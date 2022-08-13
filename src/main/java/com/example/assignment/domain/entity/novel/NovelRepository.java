package com.example.assignment.domain.entity.novel;

import com.example.assignment.domain.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovelRepository extends CrudRepository<Novel, Long> {

    List<Novel> findNovelsByUser(User user);

}
