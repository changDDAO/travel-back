package com.project.travel.common.error.exceptions;

import com.project.travel.common.error.BaseErrorCode;

public class UnauthorizedException extends CustomRuntimeException {

    public UnauthorizedException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
