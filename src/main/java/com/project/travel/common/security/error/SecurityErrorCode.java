package com.project.travel.common.security.error;

import com.project.travel.common.error.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum SecurityErrorCode implements BaseErrorCode {

    // Security 관련 익셉션
    LOGIN_FAILED_EXCEPTION(HttpStatus.UNAUTHORIZED, "로그인에 실패하였습니다."),
    NEED_LOGIN_EXCEPTION(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    AUTHORIZATION_EXCEPTION(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다."),
    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "유효하지 않는 JWT 서명 입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 JWT token 입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "지원되지 않는 JWT 토큰 입니다."),
    INVALID_JWT_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않는 JWT 토큰 입니다."),
    MISSING_CREDENTIALS(HttpStatus.BAD_REQUEST, "이메일과 비밀번호를 입력해주세요."),
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
