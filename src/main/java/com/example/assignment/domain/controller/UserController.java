package com.example.assignment.domain.controller;

import com.example.assignment.domain.controller.dto.request.CreateMemberRequest;
import com.example.assignment.domain.controller.dto.request.LoginMemberRequest;
import com.example.assignment.domain.controller.dto.request.ReIssueTokenRequest;
import com.example.assignment.domain.controller.dto.request.WithdrawalRequest;
import com.example.assignment.domain.controller.dto.response.IssueTokenResponse;
import com.example.assignment.domain.controller.dto.response.MessageResponse;
import com.example.assignment.domain.controller.dto.response.UserInfoResponse;
import com.example.assignment.domain.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse createMember(@RequestBody CreateMemberRequest request){
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

    @GetMapping("/info/{nickname}")
    public UserInfoResponse getUserInfo(@PathVariable("nickname") String nickname){
        return userService.getUserInfo(nickname);
    }

    @PostMapping("/re-issue")
    public IssueTokenResponse refresh(@RequestBody ReIssueTokenRequest request){
        return userService.refresh(request);
    }
}
