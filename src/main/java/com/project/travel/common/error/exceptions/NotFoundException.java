package com.project.travel.common.error.exceptions;

import com.project.travel.common.error.BaseErrorCode;

public class NotFoundException extends CustomRuntimeException {

    public NotFoundException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
