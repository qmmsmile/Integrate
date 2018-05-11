package com.qmm.integrate.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 *
 * @author qinmengmei
 * @date 2018/5/11
 */
@Data
@NoArgsConstructor
public class RedisParam {
    /**
     * key
     */
    @NotNull
    private String key;
    /**
     * value
     */
    @NotNull
    private String value;

    public RedisParam(@NotNull String key) {
        this.key = key;
    }
}
