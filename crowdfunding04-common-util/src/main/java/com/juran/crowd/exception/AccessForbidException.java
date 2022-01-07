package com.juran.crowd.exception;

/**
 * 作者： Juran on 2022/1/7 12:33
 * 作者博客：iit.la
 */

/**
 *用户没有登录抛出的受保护异常
 */
public class AccessForbidException extends RuntimeException{
    public AccessForbidException() {
        super();
    }

    public AccessForbidException(String message) {
        super(message);
    }

    public AccessForbidException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbidException(Throwable cause) {
        super(cause);
    }

    protected AccessForbidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
