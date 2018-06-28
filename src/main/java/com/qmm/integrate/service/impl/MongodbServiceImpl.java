package com.qmm.integrate.service.impl;

import com.qmm.integrate.mapper.CityReposity;
import com.qmm.integrate.model.po.City;
import com.qmm.integrate.service.MongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qinmengmei on 2018/6/27.
 */
@Service("mongodbService")
public class MongodbServiceImpl implements MongodbService{

    @Autowired
    private CityReposity cityReposity;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public City findCityByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        City c = mongoTemplate.findOne(query, City.class);
        return c;
    }

    @Override
    public List<City> ListCity() {
//        List<City> all = cityReposity.findAll();
        List<City> all = mongoTemplate.findAll(City.class);
        return all;
    }
}
