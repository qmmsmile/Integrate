package com.qmm.integrate.util;

import com.qmm.integrate.exception.CustomError;
import lombok.Data;


/**
 * @author qinmengmei
 */
@Data
public class DataResult<T> {
    /**
     * 返回状态
     */
    private Integer status;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 接口耗时
     */
    private Long cost;

    public static DataResult ok() {
        return ok(null);
    }

    public static <V> DataResult<V> ok(V data) {
        return fail(data, CustomError.SUCCESS);
    }

    public static DataResult fail(Integer code, String message) {
        DataResult dataResult = new DataResult();
        dataResult.setStatus(code);
        dataResult.setMessage(message);
        return dataResult;
    }

    public static DataResult fail(CustomError customError) {
        return fail(null, customError);
    }

    public static <V> DataResult<V> fail(V data, CustomError customError) {
        DataResult<V> dataResult = new DataResult<>();
        dataResult.setStatus(customError.getCode());
        dataResult.setMessage(customError.getMsg());
        dataResult.setData(data);
        return dataResult;
    }

    public static DataResult fail(CustomError customError, String summaryMsg) {
        return fail(null, customError, summaryMsg);
    }

    public static <V> DataResult<V> fail(V data, CustomError customError, String summaryMsg) {
        DataResult<V> dataResult = new DataResult<>();
        dataResult.setStatus(customError.getCode());
        dataResult.setMessage(customError.getMsg() + ": " + summaryMsg);
        dataResult.setData(data);
        return dataResult;
    }
}
