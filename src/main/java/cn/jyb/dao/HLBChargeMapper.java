package cn.jyb.dao;

import java.util.List;

import cn.jyb.entity.HLBCharge;

public interface HLBChargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HLBCharge record);

    int insertSelective(HLBCharge record);

    HLBCharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HLBCharge record);

    int updateByPrimaryKey(HLBCharge record);
    
    List<HLBCharge> listCharge();//列出全部启用的车型收费标准
    
    HLBCharge findByCarType(String carType);//通过车辆型号查找收费标准(启用中)
}