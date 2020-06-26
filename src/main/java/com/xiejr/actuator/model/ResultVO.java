package com.xiejr.actuator.model;

import com.xiejr.actuator.enums.ResultEnum;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 响应结果视图对象
 * @author: xjr
 * @create: 2020-06-17 15:29
 **/
@Data
@Accessors(chain = true)
@Builder
public class ResultVO<T> implements Serializable {


    private String code;

    private String error;

    private String message;

    private T data;

    public static <T> ResultVO<T> success(String message) {
        return ResultVO.<T>builder().code(ResultEnum.SUCCESS.getCode()).message(message).build();
    }

    public static <T> ResultVOBuilder<T> success() {
        return ResultVO.<T>builder().code(ResultEnum.SUCCESS.getCode());
    }

    public static <T> ResultVO<T> failure(String message) {
        return ResultVO.<T>builder().code(ResultEnum.FAILURE.getCode()).message(message).build();
    }

    public static <T> ResultVO<T> failure(String code, String message) {
        return ResultVO.<T>builder().code(code).message(message).build();
    }

    public static <T> ResultVOBuilder<T> failure() {
        return ResultVO.<T>builder().code(ResultEnum.FAILURE.getCode());
    }

    public static <T> ResultVO<T> data(T data) {
        return ResultVO.<T>success().data(data).build();
    }

}
