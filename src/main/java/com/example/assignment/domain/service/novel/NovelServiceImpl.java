package com.example.assignment.domain.service.novel;

import com.example.assignment.domain.controller.dto.request.EditNovelRequest;
import com.example.assignment.domain.controller.dto.request.PostNovelRequest;
import com.example.assignment.domain.controller.dto.response.MessageResponse;
import com.example.assignment.domain.controller.dto.response.NovelListTitleResponse;
import com.example.assignment.domain.controller.dto.response.NovelResponse;
import com.example.assignment.domain.controller.dto.response.NovelTitleResponse;
import com.example.assignment.domain.entity.likes.LikesRepository;
import com.example.assignment.domain.entity.novel.Novel;
import com.example.assignment.domain.entity.novel.NovelRepository;
import com.example.assignment.domain.entity.user.User;
import com.example.assignment.global.error.exception.NotFoundNovelException;
import com.example.assignment.global.error.exception.UnAuthorizedTokenException;
import com.example.assignment.global.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NovelServiceImpl implements NovelService{

    private final NovelRepository novelRepository;

    private final AuthenticationFacade authenticationFacade;

    private final LikesRepository likesRepository;

    @Override
    public MessageResponse postNovel(PostNovelRequest request) {
        User user = authenticationFacade.getCurrentUser();
        novelRepository.save(Novel.builder()
                        .user(user)
                        .category(request.getCategory())
                        .title(request.getTitle())
                        .text(request.getText())
                .build());
        return MessageResponse.builder()
                .message("작성 완료")
                .build();
    }

    @Override
    public NovelResponse getNovel(Long id) {
        Novel novel = novelRepository.findById(id)
                .orElseThrow(NotFoundNovelException::getInstance);
        return NovelResponse.builder()
                .id(novel.getId())
                .postman(novel.getUser().getNickname())
                .title(novel.getTitle())
                .text(novel.getText())
                .category(novel.getCategory())
                .likes(likesRepository.findLikesByNovel(novel).size())
                .build();
    }

    @Override
    public NovelResponse editNovel(EditNovelRequest request) {
        User user = authenticationFacade.getCurrentUser();
        Novel novel = novelRepository.findById(request.getId())
                .filter(novel1 -> novel1.getUser().equals(user))
                .orElseThrow(UnAuthorizedTokenException::getInstance);
        Novel novel1 = novelRepository.save(Novel.builder()
                        .id(novel.getId())
                        .title(request.getTitle())
                        .user(user)
                        .category(request.getCategory())
                        .text(request.getText())
                        .build());
        return NovelResponse.builder()
                .id(novel1.getId())
                .title(novel1.getTitle())
                .postman(novel1.getUser().getNickname())
                .category(novel1.getCategory())
                .text(novel1.getText())
                .likes(likesRepository.findLikesByNovel(novel1).size())
                .build();
    }

    @Override
    public MessageResponse deleteNovel(Long id) {
        Novel novel =  novelRepository.findById(id)
                .orElseThrow(NotFoundNovelException::getInstance);
        if(novel.getUser().equals(authenticationFacade.getCurrentUser())){
            novelRepository.deleteById(id);
            return MessageResponse.builder()
                    .message("소설 삭제 완료")
                    .build();
        }
        throw UnAuthorizedTokenException.getInstance();
    }

    @Override
    public NovelListTitleResponse getLikesNovel() {
        List<NovelTitleResponse> novelList = likesRepository.findLikesByUser(authenticationFacade.getCurrentUser()).stream()
                .map(likes -> NovelTitleResponse.builder()
                        .id(likes.getNovel().getId())
                        .title(likes.getNovel().getTitle())
                        .postman(likes.getNovel().getUser().getNickname())
                        .category(likes.getNovel().getCategory())
                        .build())
                .collect(Collectors.toList());
        return NovelListTitleResponse.builder()
                .novelList(novelList)
                .build();
    }

}
