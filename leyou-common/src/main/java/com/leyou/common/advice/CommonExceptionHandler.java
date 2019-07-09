package com.leyou.common.advice;

import com.leyou.common.exception.LeyouException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.leyou.common.advice
 * @date:2019/7/8
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(LeyouException.class)
    public ResponseEntity<ExceptionResult> handleException(LeyouException e) {
        return ResponseEntity.status(e.getExceptionEnums().getCode())
                .body(new ExceptionResult(e.getExceptionEnums()));
    }
}
