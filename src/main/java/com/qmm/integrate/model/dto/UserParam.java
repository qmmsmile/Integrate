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
public class UserParam {
    /**
     * pageNo
     *@NotNull
     */
    private Integer pageNo;
    /**
     * pageSize
     * @NotNull
     */
    private Integer pageSize;

    /**
     * keyword
     */
    private String keyword;
}
