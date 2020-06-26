package com.xiejr.actuator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @version: 1.0
 * @author: xiejiarong
 * @date 3/18/2020
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS("200" , "请求成功"),
    FAILURE("500" , "请求失败"),
    FORBIDDEN("403" , "没有权限"),
    CUSTOMFAILURE("501","业务异常");

    private String code;
    private String name;

    public static ResultEnum getByCode(String code) {
        for (ResultEnum sellerStatusEnum : ResultEnum.values()) {
            if (sellerStatusEnum.getCode().equals(code)) {
                return sellerStatusEnum;
            }
        }
        return null;
    }
}
