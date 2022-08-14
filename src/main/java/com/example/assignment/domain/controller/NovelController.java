package com.example.assignment.domain.controller;

import com.example.assignment.domain.controller.dto.request.EditNovelRequest;
import com.example.assignment.domain.controller.dto.request.PostNovelRequest;
import com.example.assignment.domain.controller.dto.response.MessageResponse;
import com.example.assignment.domain.controller.dto.response.NovelListTitleResponse;
import com.example.assignment.domain.controller.dto.response.NovelResponse;
import com.example.assignment.domain.service.novel.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/novel")
@RequiredArgsConstructor
public class NovelController {

    private final NovelService novelService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse postNovel(@RequestBody PostNovelRequest request){
        return novelService.postNovel(request);
    }

    @GetMapping("/{novel-id}")
    public NovelResponse getNovel(@PathVariable("novel-id") Long id){
        return novelService.getNovel(id);
    }

    @PatchMapping("/edit")
    public NovelResponse editNovel(@RequestBody EditNovelRequest request){
        return novelService.editNovel(request);
    }

    @DeleteMapping("{novel-id}")
    public MessageResponse deleteNovel(@PathVariable("novel-id") Long id){
        return novelService.deleteNovel(id);
    }

    @GetMapping("/likes")
    public NovelListTitleResponse getLikesNovel(){
        return novelService.getLikesNovel();
    }
}
