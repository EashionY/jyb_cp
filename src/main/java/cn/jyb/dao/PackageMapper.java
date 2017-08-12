package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import cn.jyb.entity.Package;

public interface PackageMapper {
    int deleteByPrimaryKey(Integer packageId);

    int insert(Package record);

    int insertSelective(Package record);

    Package selectByPrimaryKey(Integer packageId);

    int updateByPrimaryKeySelective(Package record);

    int updateByPrimaryKey(Package record);

    /**
     * 查找该驾校的报名套餐
     * @param school_id
     * @return
     */
	List<Map<String, Object>> findBySchoolId(int school_id);
}