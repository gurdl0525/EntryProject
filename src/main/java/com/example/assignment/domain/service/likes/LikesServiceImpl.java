package com.example.assignment.domain.service.likes;

import com.example.assignment.domain.controller.dto.response.MessageResponse;
import com.example.assignment.domain.entity.likes.Likes;
import com.example.assignment.domain.entity.likes.LikesRepository;
import com.example.assignment.domain.entity.novel.Novel;
import com.example.assignment.domain.entity.novel.NovelRepository;
import com.example.assignment.domain.entity.user.User;
import com.example.assignment.global.error.exception.AlreadyLikeException;
import com.example.assignment.global.error.exception.BadRequestException;
import com.example.assignment.global.error.exception.NotFoundNovelException;
import com.example.assignment.global.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService{

    private final NovelRepository novelRepository;

    private final AuthenticationFacade authenticationFacade;

    private final LikesRepository likesRepository;

    @Override
    public MessageResponse like(Long id){
        Novel novel = novelRepository.findById(id)
                .orElseThrow(NotFoundNovelException::getInstance);
        User user = authenticationFacade.getCurrentUser();
        if(novel.getUser().equals(user)){
            throw BadRequestException.getInstance();
        }
        if(likesRepository.existsByNovelAndUser(novel, user)){
            throw AlreadyLikeException.getInstance();
        }
        likesRepository.save(Likes.builder()
                .user(user)
                .novel(novel)
                .build());
        return MessageResponse.builder()
                .message("팔로우 완료")
                .build();
    }

    @Override
    public MessageResponse unLike(Long id){
        Novel novel = novelRepository.findById(id)
                .orElseThrow(NotFoundNovelException::getInstance);
        User user = authenticationFacade.getCurrentUser();
        if(novel.getUser().equals(user)){
            throw BadRequestException.getInstance();
        }
        Likes likes = likesRepository.findByNovelAndUser(novel, user)
                .orElseThrow(BadRequestException::getInstance);
        likesRepository.deleteById(likes.getId());
        return MessageResponse.builder()
                .message("좋아요 취소 완료")
                .build();
    }
}
