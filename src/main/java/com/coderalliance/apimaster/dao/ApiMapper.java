package com.coderalliance.apimaster.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderalliance.apimaster.model.entity.Api;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiMapper extends BaseMapper<Api> {
}
