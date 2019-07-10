package com.leyou.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.leyou.config
 * @date:2019/7/10
 */
@Data
@ConfigurationProperties(prefix = "leyou.upload")
public class UploadProperties {

    private String baseUrl;
    private List<String> allowTypes;
}
