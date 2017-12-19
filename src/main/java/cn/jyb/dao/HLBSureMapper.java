package cn.jyb.dao;

import cn.jyb.entity.HLBSure;

public interface HLBSureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HLBSure record);

    int insertSelective(HLBSure record);

    HLBSure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HLBSure record);

    int updateByPrimaryKey(HLBSure record);

	HLBSure findByOrderNo(String hlbOrderNo);
}