package com.qmm.integrate.service;

import com.qmm.integrate.model.dto.UserParam;
import com.qmm.integrate.model.po.User;

import java.util.List;

/**
 *
 * @author qinmengmei
 * @date 2018/5/11
 */
public interface UserService {

    List<User> listUser(UserParam param);
}
