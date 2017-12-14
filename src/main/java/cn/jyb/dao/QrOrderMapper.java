package cn.jyb.dao;

import cn.jyb.entity.QrOrder;

public interface QrOrderMapper {
    int deleteByPrimaryKey(String qrOrderNo);

    int insert(QrOrder record);

    int insertSelective(QrOrder record);

    QrOrder selectByPrimaryKey(String qrOrderNo);

    int updateByPrimaryKeySelective(QrOrder record);

    int updateByPrimaryKey(QrOrder record);
}