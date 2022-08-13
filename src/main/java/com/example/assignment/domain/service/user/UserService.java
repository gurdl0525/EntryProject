package com.example.assignment.domain.service.user;

import com.example.assignment.domain.controller.dto.request.CreateMemberRequest;
import com.example.assignment.domain.controller.dto.request.LoginMemberRequest;
import com.example.assignment.domain.controller.dto.request.ReIssueTokenRequest;
import com.example.assignment.domain.controller.dto.request.WithdrawalRequest;
import com.example.assignment.domain.controller.dto.response.IssueTokenResponse;
import com.example.assignment.domain.controller.dto.response.MessageResponse;
import com.example.assignment.domain.controller.dto.response.NovelListTitleResponse;
import com.example.assignment.domain.controller.dto.response.UserInfoResponse;

public interface UserService {
    MessageResponse join(CreateMemberRequest request);

    IssueTokenResponse login(LoginMemberRequest request);

    MessageResponse withdrawal(WithdrawalRequest request);

    UserInfoResponse getUserInfo(String nickname);

    IssueTokenResponse refresh(ReIssueTokenRequest request);
}
