package com.qmm.integrate.controller;

import com.qmm.integrate.model.dto.RedisParam;
import com.qmm.integrate.model.dto.UserParam;
import com.qmm.integrate.model.po.User;
import com.qmm.integrate.service.RedisService;
import com.qmm.integrate.service.UserService;
import com.qmm.integrate.util.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qinmengmei
 * @date 2018/5/11
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    /**
     * mysql 获取所有联系人，分页
     * @param param
     * @return
     */
    @GetMapping(value = "/listUser")
    public DataResult<List<User>> listUser(@Validated UserParam param) {
        log.info("listUser");
        long start = System.currentTimeMillis();
        List<User> users = userService.listUser(param);
        long end = System.currentTimeMillis();
        DataResult<List<User>> result = DataResult.ok(users);
        result.setCost(end -start);
        return result;
    }

    /**
     * Redis 添加数据
     * @param param
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST , produces = "application/json;charset=UTF-8" )
    public DataResult save(@Validated @RequestBody RedisParam param){
        long start = System.currentTimeMillis();
        redisService.set(param.getKey(),param.getValue());
        long end = System.currentTimeMillis();
        DataResult result = DataResult.ok();
        result.setCost(end -start);
        return result;
    }

    /**
     * Redis 保存map形式
     * @return
     */
    @GetMapping(value = "/saveMap")
    public DataResult saveMap(){
        String key = "hmset";
        Map map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");
        map.put("k4","v4");
        map.put("k5","v5");
        redisService.hmset(key,map);
        return DataResult.ok();
    }

}
