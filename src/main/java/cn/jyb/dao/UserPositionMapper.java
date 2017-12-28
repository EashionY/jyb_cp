package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.UserPosition;

public interface UserPositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPosition record);

    int insertSelective(UserPosition record);

    UserPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPosition record);

    int updateByPrimaryKey(UserPosition record);
    
    UserPosition findByUserId(Integer userId);
    
    List<Map<String,Object>> listUserByDistance(@Param("userLon")double userLon,@Param("userLat")double userLat,
    		@Param("region")String region,@Param("distance")Integer distance);
    
}