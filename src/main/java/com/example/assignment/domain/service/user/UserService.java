package com.example.assignment.domain.service.user;

import com.example.assignment.domain.controller.dto.request.*;
import com.example.assignment.domain.controller.dto.response.IssueTokenResponse;
import com.example.assignment.domain.controller.dto.response.MessageResponse;
import com.example.assignment.domain.controller.dto.response.UserInfoResponse;

public interface UserService {
    MessageResponse join(CreateMemberRequest request);

    IssueTokenResponse login(LoginMemberRequest request);

    MessageResponse withdrawal(WithdrawalRequest request);

    UserInfoResponse getUserInfo(String accountId);

    IssueTokenResponse refresh(ReIssueTokenRequest request);

    MessageResponse editNickname(EditNicknameRequest request);

    MessageResponse editPassword(EditPasswordRequest request);
}
