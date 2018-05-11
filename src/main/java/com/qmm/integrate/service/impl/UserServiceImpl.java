package com.qmm.integrate.service.impl;

import com.github.pagehelper.Page;
import com.qmm.integrate.mapper.UserMapper;
import com.qmm.integrate.model.dto.UserParam;
import com.qmm.integrate.model.po.User;
import com.qmm.integrate.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author qinmengmei
 * @date 2018/5/11
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUser(UserParam param){
        Integer pageNo = param.getPageNo();
        Integer pageSize = param.getPageSize();
        Page<User> users = userMapper.listByPage(param.getKeyword(), new RowBounds(pageNo, pageSize));
        return users;
    }
}
