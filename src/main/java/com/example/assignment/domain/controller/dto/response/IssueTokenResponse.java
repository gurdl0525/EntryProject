package com.example.assignment.domain.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IssueTokenResponse {

    private String accessToken;

    private String refreshToken;

}
