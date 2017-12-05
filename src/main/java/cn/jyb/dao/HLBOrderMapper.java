package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.HLBOrder;

public interface HLBOrderMapper {
    int deleteByPrimaryKey(String hlbOrderNo);

    int insert(HLBOrder record);

    int insertSelective(HLBOrder record);

    HLBOrder selectByPrimaryKey(String hlbOrderNo);

    int updateByPrimaryKeySelective(HLBOrder record);

    int updateByPrimaryKey(HLBOrder record);
    
    List<HLBOrder> listBookOrders(@Param("receiptId")Integer receiptId,@Param("offset")Integer offset,
    		@Param("pageSize")Integer pageSize);
    
    double getDriverScore(Integer receiptId);//车主评分
    
    int getDriverOrderNum(Integer receiptId);//车主已接订单数量
}