package com.project.travel.domain.user.error;

import com.project.travel.common.error.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    ALREADY_REGISTERED_NICKNAME(HttpStatus.CONFLICT, "이미 등록된 닉네임입니다."),
    NEW_PASSWORD_MATCHES_CURRENT_PASSWORD(HttpStatus.CONFLICT, "변경할 비밀번호가 등록된 비밀번호와 일치합니다."),
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
