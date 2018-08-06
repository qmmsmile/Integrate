package com.qmm.integrate.service.impl;

import com.qmm.integrate.cache.EhCacheBiz;
import com.qmm.integrate.mapper.CityReposity;
import com.qmm.integrate.model.po.City;
import com.qmm.integrate.service.MongodbService;
import com.qmm.integrate.util.DateUtils;
import com.qmm.integrate.util.EmptyUtils;
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
    @Autowired
    private EhCacheBiz ehCacheBiz;

    @Override
    public City findCityByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        City c = mongoTemplate.findOne(query, City.class);
        return c;
    }

    @Override
    public List<City> ListCity() {
//        List<City> all = cityReposity.findAll();
        List<City> city = (List<City>)ehCacheBiz.get("city");
        if (EmptyUtils.isEmpty(city)){
            city = mongoTemplate.findAll(City.class);
            String key = "city";
            String statisDate = DateUtils.nowDate(DateUtils.YYYYMMDD_DATE_PATTERN);
            // 可以对缓存数据做持久化操作，00:01时ehcache中所有缓存失效
            int validSeconds = (int)DateUtils.getTomorrowZeroSeconds() + 60;
            ehCacheBiz.putSpecifiedTimeAndFlush(key,city,validSeconds);
        }
        return city;
    }
}
