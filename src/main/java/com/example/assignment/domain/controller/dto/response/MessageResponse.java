package com.example.assignment.domain.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageResponse {
    private final String message;
}
