package com.sy;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.object.code.ErrorCode;
import com.object.exception.ErrorCodeException;
import com.object.resp.BaseResp;
import com.sy.controller.BaseController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    @ExceptionHandler(value = ErrorCodeException.class)
    public BaseResp exceptionHandler(ErrorCodeException exception) {

        log.error("{}", failure(exception.getCode(), exception.getMessage()));
        log.error("{}", exception);
        return failure(exception.getCode(), exception.getMessage());
    }
    
    @ExceptionHandler(Throwable.class)
    public BaseResp handle(Throwable ex) {
        log.error("{}", ex);
        
        return failure(ErrorCode.INTERNAL_ERROR);
    }
}
