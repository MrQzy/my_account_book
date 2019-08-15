package com.qzy.mab.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qzy.mab.exception.AuthenticateTokenException;
import com.qzy.mab.exception.AuthenticateTokenInvalidException;
import com.qzy.mab.exception.MabRemoteBusinessException;
import com.qzy.mab.support.CustomHttpStatus;
import com.qzy.mab.support.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GloablExceptionHandler {

    @ExceptionHandler(AuthenticateTokenInvalidException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED) ResponseEntity<String> handlerUnauthorized(Throwable t) {
        ResponseEntity<String> err = new ResponseEntity<String>();
        err.setCode(CustomHttpStatus.NOT_LOGIN.value());
        err.setMsg("用户未登录");
        log.error("用户未登录", t);
        return err;
    }

    @ExceptionHandler
    ResponseEntity<String> handler(Throwable t) {
        ResponseEntity<String> err = new ResponseEntity<>();
        err.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        if (t instanceof AuthenticateTokenException) {
            log.info("登录失败,账号或者密码不正确!");
            err.setCode(CustomHttpStatus.PASSWORD_ERROR_MORE_TIMRES_EXCEPTION.value());
            err.setMsg(t.getMessage());
        } else if (t instanceof MabRemoteBusinessException) {
            // 业务异常
            err.setCode(CustomHttpStatus.BUSINESS_EXCEPTION.value());
            err.setMsg(t.getMessage());
        } else if (t instanceof IllegalArgumentException) {
            // 请求错误
            err.setCode(CustomHttpStatus.BAD_REQUEST.value());
            err.setMsg(t.getMessage());
        } else {
            // 系统内部错误
            err.setCode(CustomHttpStatus.INTERNAL_SERVER_ERROR.value());
            err.setMsg("服务器内部错误");
        }
        log.error("出错了:", t);
        return err;
    }

}
