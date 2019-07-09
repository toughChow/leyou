package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.leyou.common.exception
 * @date:2019/7/8
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LeyouException extends RuntimeException {

    private ExceptionEnums exceptionEnums;
}
