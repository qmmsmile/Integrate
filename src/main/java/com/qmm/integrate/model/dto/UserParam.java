package com.qmm.integrate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author qinmengmei
 * @date 2018/5/11
 */
@Data
public class UserParam {
    /**
     * pageNo
     */
    @NotNull
    private Integer pageNo;
    /**
     * pageSize
     */
    @NotNull
    private Integer pageSize;
}
