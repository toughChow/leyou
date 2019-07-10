package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.leyou.common.enums
 * @date:2019/7/8
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnums {

    CATEGORY_NOT_FIND(404, "商品分类没查到"),
    BRAND_SAVE_ERROR(500, "新增品牌失败"),
    INVALID_FILE_TYPE(400, "无效文件类型"),
    UPLOAD_FILE_ERROR(500, "文件上传失败"),
    ;
    private int code;
    private String msg;

}
