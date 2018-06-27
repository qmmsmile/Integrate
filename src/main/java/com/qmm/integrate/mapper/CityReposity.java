package com.qmm.integrate.mapper;

import com.qmm.integrate.model.po.City;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by qinmengmei on 2018/6/27.
 */
public interface CityReposity extends MongoRepository<City, String> {

}
