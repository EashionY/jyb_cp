package cn.jyb.dao;

import cn.jyb.entity.Environment;

public interface EnvironmentMapper {
    int deleteByPrimaryKey(Integer environmentId);

    int insert(Environment record);

    int insertSelective(Environment record);

    Environment selectByPrimaryKey(Integer environmentId);

    int updateByPrimaryKeySelective(Environment record);

    int updateByPrimaryKey(Environment record);
    
    Environment findBySchoolId(Integer school_id);
}