package com.xiejr.actuator.entity.dto;

import lombok.Data;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 文档下载数据传输对象
 * @author: xjr
 * @create: 2020-06-27 18:00
 **/
@Data
public class DocumentDownloadDto {

    /**
     * 前端url
     */
    private String hostUrl;

    /**
     * 语言
     */
    private String language;

    /**
     * 文件类型
     */
    private String fileType;
}
