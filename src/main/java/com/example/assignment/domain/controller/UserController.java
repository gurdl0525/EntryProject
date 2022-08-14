package com.example.assignment.domain.controller;

import com.example.assignment.domain.controller.dto.request.*;
import com.example.assignment.domain.controller.dto.response.IssueTokenResponse;
import com.example.assignment.domain.controller.dto.response.MessageResponse;
import com.example.assignment.domain.controller.dto.response.UserInfoResponse;
import com.example.assignment.domain.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse createMember(@Valid @RequestBody CreateMemberRequest request){
        return userService.join(request);
    }

    @PostMapping("/login")
    public IssueTokenResponse login(@RequestBody LoginMemberRequest request){
        return  userService.login(request);
    }

    @DeleteMapping
    public MessageResponse withdrawal(@RequestBody WithdrawalRequest request){
        return userService.withdrawal(request);
    }

    @GetMapping("/info/{account_id}")
    public UserInfoResponse getUserInfo(@PathVariable("account_id") String accountId){
        return userService.getUserInfo(accountId);
    }

    @PostMapping("/re-issue")
    public IssueTokenResponse refresh(@RequestBody ReIssueTokenRequest request){
        return userService.refresh(request);
    }

    @PatchMapping("/edit/nickname")
    public MessageResponse editNickname(@RequestBody EditNicknameRequest request){
        return userService.editNickname(request);
    }

    @PatchMapping("/edit/password")
    public MessageResponse editPassword(@RequestBody EditPasswordRequest request){
        return userService.editPassword(request);
    }
}
