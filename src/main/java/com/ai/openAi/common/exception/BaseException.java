package com.ai.openAi.common.exception;

import com.ai.openAi.common.Constants;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final String msg;
    private final int code;

    public BaseException(IError error) {
        super(error.msg());
        this.code = error.code();
        this.msg = error.msg();
    }

    public BaseException(String msg) {
        super(msg);
        this.code = Constants.ErrorMsg.SYS_ERROR.code();
        this.msg = msg;
    }

    public BaseException() {
        super(Constants.ErrorMsg.SYS_ERROR.msg());
        this.code = Constants.ErrorMsg.SYS_ERROR.code();
        this.msg = Constants.ErrorMsg.SYS_ERROR.msg();
    }
}
