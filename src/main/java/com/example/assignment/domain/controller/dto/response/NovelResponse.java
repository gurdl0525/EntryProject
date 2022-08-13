package com.example.assignment.domain.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NovelResponse {

    private Long id;

    private String postman;

    private String title;

    private String text;

    private Long category;

    private Integer follower;

}
