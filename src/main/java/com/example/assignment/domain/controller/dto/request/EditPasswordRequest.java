package com.example.assignment.domain.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditPasswordRequest {

    private String oldPassword;

    private String changePassword;

}
