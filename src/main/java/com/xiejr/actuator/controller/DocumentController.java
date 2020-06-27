package com.xiejr.actuator.controller;

import com.xiejr.actuator.entity.constant.ConstantConfig;
import com.xiejr.actuator.entity.dto.DocumentDownloadDto;
import com.xiejr.actuator.model.ResultVO;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.web.Swagger2Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: api文档接口服务
 * @author: xjr
 * @create: 2020-06-27 17:59
 **/
@RestController
@Api("文档下载")
@RequestMapping(ConstantConfig.PREFIX+"/document")
@Lazy
public class DocumentController {

    @Autowired(required = false)
    Docket docket;

    @PostMapping("download")
    public ResultVO<String> dowmloadSwaggerapi(@RequestBody DocumentDownloadDto dowmloadSwaggerapiForm, HttpServletResponse response) throws IOException {
        String fileName = "document-api" + "." + dowmloadSwaggerapiForm.getFileType();
        //    输出Ascii到单文件
        MarkupLanguage markupLanguage = null;
        switch (dowmloadSwaggerapiForm.getFileType()) {
            case "md":
                markupLanguage = MarkupLanguage.MARKDOWN;
                break;
            case "adoc":
                markupLanguage = MarkupLanguage.ASCIIDOC;
                break;
            case "txt":
                markupLanguage = MarkupLanguage.CONFLUENCE_MARKUP;
                break;
            default:
                fileName = fileName + "md";
                markupLanguage = MarkupLanguage.MARKDOWN;
                break;

        }
        Language language = "ZH".equals(dowmloadSwaggerapiForm.getLanguage()) ? Language.ZH : Language.EN;
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(markupLanguage)
                .withOutputLanguage(language)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        String txt = Swagger2MarkupConverter.from(new URL(dowmloadSwaggerapiForm.getHostUrl() + Swagger2Controller.DEFAULT_URL+"?group="+docket.getGroupName()))
                .withConfig(config)
                .build()
                .toString();
        return ResultVO.data(txt);


    }

}
