package com.example.assignment.domain.service.novel;

import com.example.assignment.domain.controller.dto.request.EditNovelRequest;
import com.example.assignment.domain.controller.dto.request.PostNovelRequest;
import com.example.assignment.domain.controller.dto.response.MessageResponse;
import com.example.assignment.domain.controller.dto.response.NovelListTitleResponse;
import com.example.assignment.domain.controller.dto.response.NovelResponse;

public interface NovelService {
    MessageResponse postNovel(PostNovelRequest request);

    NovelResponse getNovel(Long id);

    MessageResponse deleteNovel(Long id);

    NovelListTitleResponse getLikesNovel();

    NovelResponse editNovel(EditNovelRequest request);
}
