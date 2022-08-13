package com.example.assignment.domain.controller.dto.request;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReIssueTokenRequest {

    private String refreshToken;

}
