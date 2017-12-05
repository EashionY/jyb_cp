package cn.jyb.dao;

import cn.jyb.entity.WxOpenid;

public interface WxOpenidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxOpenid record);

    int insertSelective(WxOpenid record);

    WxOpenid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxOpenid record);

    int updateByPrimaryKey(WxOpenid record);
    
    WxOpenid findByOpenid(String openid);
    
    WxOpenid findByUserId(Integer userId);
}