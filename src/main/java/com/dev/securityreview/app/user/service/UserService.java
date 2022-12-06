package com.dev.securityreview.app.user.service;

import com.dev.securityreview.app.user.dao.dto.UserDto;
import com.dev.securityreview.app.user.dao.dto.UserJoinRequest;
import com.dev.securityreview.app.user.dao.entity.User;
import com.dev.securityreview.app.user.exception.ErrorCode;
import com.dev.securityreview.app.user.exception.UserAppException;
import com.dev.securityreview.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto joinUser(UserJoinRequest userJoinRequest) {
        // 회원가입 로직
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent(user -> {
                    throw new UserAppException(ErrorCode.DUPLICATED_USER_NAME, userJoinRequest.getUserName());
                });

        User savedUser = userRepository.save(userJoinRequest.toEntity(userJoinRequest.getPassword()));
        return UserDto.builder()
                .userName(savedUser.getUserName())
                .password(savedUser.getPassword())
                .emailAddress(savedUser.getEmailAddress())
                .build();
    }
}
