package com.qmm.integrate.service;

import com.qmm.integrate.model.po.City;

/**
 * Created by qinmengmei on 2018/6/27.
 */
public interface MongodbService {

    City findCityByName(String name);
}
