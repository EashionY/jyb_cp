package cn.jyb.dao;

import cn.jyb.entity.IdCard;

public interface IdCardMapper {
    int deleteByPrimaryKey(Integer idcardId);

    int insert(IdCard record);

    int insertSelective(IdCard record);

    IdCard selectByPrimaryKey(Integer idcardId);

    int updateByPrimaryKeySelective(IdCard record);

    int updateByPrimaryKey(IdCard record);
    
    IdCard findByUserId(Integer userId);
}