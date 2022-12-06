package com.dev.securityreview.app.user.controller;

import com.dev.securityreview.app.user.dao.dto.UserDto;
import com.dev.securityreview.app.user.dao.dto.UserJoinRequest;
import com.dev.securityreview.app.user.exception.ErrorCode;
import com.dev.securityreview.app.user.exception.UserAppException;
import com.dev.securityreview.app.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void joinUser_Succeess() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("TestUser")
                .password("1234")
                .emailAddress("test@test.test")
                .build();

        when(userService.joinUser(any()))
                .thenReturn(mock(UserDto.class));

        mockMvc.perform(post("/api/v1/users/join").with(csrf()) //  Use csrf to pass through Spring Security
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패")
    void joinUser_fail() {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("TestUser")
                .password("1234")
                .emailAddress("test@test.test")
                .build();

        when(userService.joinUser(any()))
                .thenThrow(new UserAppException(ErrorCode.DUPLICATED_USER_NAME, ""));
    }
}