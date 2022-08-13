package com.example.assignment.domain.controller;

import com.example.assignment.domain.controller.dto.response.MessageResponse;
import com.example.assignment.domain.service.likes.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/like/{novel-id}")
    public MessageResponse like(@PathVariable("novel-id") Long id){
        return likesService.like(id);
    }

    @DeleteMapping("/unlike/{novel-id}")
    public MessageResponse unLike(@PathVariable("novel-id") Long id){
        return likesService.unLike(id);
    }

}
