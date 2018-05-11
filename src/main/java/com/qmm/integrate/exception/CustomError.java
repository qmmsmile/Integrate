package com.qmm.integrate.exception;

import lombok.Getter;


/**
 * @author qinmengmei
 */

@Getter
public enum CustomError {
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(5000, "未知服务器错误"),
    ERROR_PARAMS(5050, "入参校验错误:"),
    ERROR_DAY(5051, "日期错误:"),
    SUCCESS(-10000000, "success"),

    ENCRYPT_ERROR(3002, "加密算法出错");

    /**
     * 错误代码.
     */
    private Integer code;

    /**
     * 错误消息.
     */
    private String msg;

    CustomError(final Integer code, final String msg) {
        this.code = addBasic(code);
        this.msg = msg;
    }

    /**
     * 起始错误编码.
     */
    private static final int START_CODE = 10000000;
    /**
     * 从10000000开始编码.
     * @param code 简单代码
     * @return 全码
     */
    private Integer addBasic(final Integer code) {
        return START_CODE + code;
    }
}
