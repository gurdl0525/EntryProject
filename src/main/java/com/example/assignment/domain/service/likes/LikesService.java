package com.example.assignment.domain.service.likes;

import com.example.assignment.domain.controller.dto.response.MessageResponse;

public interface LikesService {

    MessageResponse like(Long id);

    MessageResponse unLike(Long id);

}
