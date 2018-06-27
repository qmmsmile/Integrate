package com.qmm.integrate.model.po;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *
 * @author qinmengmei
 * @date 2018/6/27
 */
@Data
public class City {

    @Id
    private String id;
    private String name;
    private String desc;
}
