package com.project.travel.common.error.exceptions;

import com.project.travel.common.error.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomRuntimeException extends RuntimeException {

    private final BaseErrorCode errorCode;
}
