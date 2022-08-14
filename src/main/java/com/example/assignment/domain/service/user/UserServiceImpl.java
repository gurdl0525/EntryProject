package com.example.assignment.domain.service.user;

import com.example.assignment.domain.controller.dto.request.*;
import com.example.assignment.domain.controller.dto.response.*;
import com.example.assignment.domain.entity.novel.NovelRepository;
import com.example.assignment.domain.entity.redis.RefreshToken;
import com.example.assignment.domain.entity.redis.RefreshTokenRepository;
import com.example.assignment.domain.entity.user.User;
import com.example.assignment.domain.entity.user.UserRepository;
import com.example.assignment.global.error.exception.*;
import com.example.assignment.global.security.JwtTokenProvider;
import com.example.assignment.global.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final NovelRepository novelRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public MessageResponse join(CreateMemberRequest request) {
        nullChecker(request.getAccountId());
        nullChecker(request.getNickname());
        nullChecker(request.getPassword());
        checkDuplicateMember(request);
        userRepository.save(User.builder()
                        .accountId(request.getAccountId())
                        .nickname(request.getNickname())
                        .password(passwordEncoder.encode(request.getPassword()))
                .build());
        return MessageResponse.builder()
                .message("회원가입 완료")
                .build();
    }
    private void nullChecker(String request){
        if(request == null || request.equals("")){
            throw BadRequestException.getInstance();
        }
    }
    private void checkDuplicateMember(CreateMemberRequest request){
        userRepository.findByAccountId(request.getAccountId())
                .ifPresent(m -> {
                    throw DuplicateMemberException.getInstance();
                });
        userRepository.findByNickname(request.getNickname())
                .ifPresent(m -> {
                    throw DuplicateNicknameException.getInstance();
                });
    }

    @Override
    public IssueTokenResponse login(LoginMemberRequest request){
        nullChecker(request.getAccountId());
        nullChecker(request.getPassword());
        User user = verifyUser(request);
        IssueTokenResponse response = generateIssueTokenResponse(user);
        refreshTokenRepository.save(new RefreshToken(user.getAccountId(), response.getRefreshToken()));
        return response;
    }

    private User verifyUser(LoginMemberRequest request){
        userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(NotFoundUserException::getInstance);
        return userRepository.findByAccountId(request.getAccountId())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .orElseThrow(PasswordInconsistencyException::getInstance);
    }

    private IssueTokenResponse generateIssueTokenResponse(User user){
        return IssueTokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId()))
                .build();
    }

    @Override
    public MessageResponse withdrawal(WithdrawalRequest request) {
        User user = authenticationFacade.getCurrentUser();
        if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
            userRepository.deleteById(user.getId());
            return MessageResponse.builder()
                    .message("회원탈퇴 완료")
                    .build();
        }
        throw PasswordInconsistencyException.getInstance();
    }

    @Override
    public UserInfoResponse getUserInfo(String accountId) {
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(NotFoundUserException::getInstance);
        return UserInfoResponse.builder()
                .nickname(user.getNickname())
                .novelListTitle(novelRepository.findNovelsByUser(user).stream()
                        .map(novel -> NovelTitleResponse.builder()
                                .id(novel.getId())
                                .title(novel.getTitle())
                                .postman(novel.getUser().getNickname())
                                .category(novel.getCategory())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public IssueTokenResponse refresh(ReIssueTokenRequest request){
        String refreshToken = request.getRefreshToken();
        String userId = jwtTokenProvider.getUserId(refreshToken);
        refreshTokenRepository.findById(userId)
                .filter(rf ->  rf.getRefreshToken().equals(refreshToken))
                .orElseThrow(UnAuthorizedTokenException::getInstance);
        return IssueTokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(userId))
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public MessageResponse editNickname(EditNicknameRequest request) {
        User user = authenticationFacade.getCurrentUser();
        userRepository.save(User.builder()
                .id(user.getId())
                .accountId(user.getAccountId())
                .password(user.getPassword())
                .nickname(request.getNickname())
                .novelList(user.getNovelList())
                .build());
        return MessageResponse.builder()
                .message("닉네임이 " + request.getNickname() + "으로 변경되었습니다.")
                .build();
    }

    @Override
    public MessageResponse editPassword(EditPasswordRequest request) {
        User user = authenticationFacade.getCurrentUser();
        if(passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            userRepository.save(User.builder()
                    .id(user.getId())
                    .accountId(user.getAccountId())
                    .password(passwordEncoder.encode(request.getChangePassword()))
                    .nickname(user.getNickname())
                    .novelList(user.getNovelList())
                    .build());
            return MessageResponse.builder()
                    .message("비밀번호가 변경되었습니다.")
                    .build();
        } throw PasswordInconsistencyException.getInstance();
    }

}

