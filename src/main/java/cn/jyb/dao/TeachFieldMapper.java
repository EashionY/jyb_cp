package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import cn.jyb.entity.TeachField;

public interface TeachFieldMapper {
    int deleteByPrimaryKey(Integer fieldId);

    int insert(TeachField record);

    int insertSelective(TeachField record);

    TeachField selectByPrimaryKey(Integer fieldId);

    int updateByPrimaryKeySelective(TeachField record);

    int updateByPrimaryKey(TeachField record);
    
    /**
     * 查找对应驾校的训练场地
     * @param school_id
     * @return
     */
    List<Map<String,Object>> findBySchoolId(int school_id);
    
    
}