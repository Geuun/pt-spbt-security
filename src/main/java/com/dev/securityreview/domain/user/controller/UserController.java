package com.dev.securityreview.domain.user.controller;

import com.dev.securityreview.domain.user.dao.Response;
import com.dev.securityreview.domain.user.dao.dto.UserDto;
import com.dev.securityreview.domain.user.dao.dto.UserJoinRequest;
import com.dev.securityreview.domain.user.dao.dto.UserJoinResponse;
import com.dev.securityreview.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
}
