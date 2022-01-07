package com.juran.crowd.exception;

/**
 * 作者： Juran on 2022/1/5 15:24
 * 作者博客：iit.la
 */

/**
 * 登录失败后抛出的异常
 */
public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
