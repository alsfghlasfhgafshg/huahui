package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface PhoneRepository {

    Map<String, Object> todayData(@Param("shopid") int shopid);
}
