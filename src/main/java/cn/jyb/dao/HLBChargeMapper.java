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
    
    List<HLBCharge> listCharge();//�г�ȫ�����õĳ����շѱ�׼
    
    HLBCharge findByCarType(String carType);//ͨ�������ͺŲ����շѱ�׼(������)
}