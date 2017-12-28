package cn.jyb.dao;

import java.util.List;
import java.util.Map;

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
    
    Double getDriverScore(Integer receiptId);//车主评分
    
    int getDriverOrderNum(Integer receiptId);//车主已接订单数量
    
    Double getPassengerScore(Integer publishId);//乘客信任值
    
    int getPassengerOrderNum(Integer publishId);//乘客完成的订单数
    
    int getOrderNum(@Param("carType")String carType,@Param("orderType")Integer orderType);//获取订单数
    /**
     * 订单列表（抢单大厅）
     * @param carType
     * @param orderType
     * @param price 1/0，价格排序
     * @param offset
     * @param pageSize
     * @return
     */
    List<Map<String,Object>> listOrders(@Param("carType")String carType,@Param("orderType")Integer orderType,
    		@Param("lon")double lon,@Param("lat")double lat,@Param("price")String price,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    /**
     * 抢单大厅，订单列表，按照距离排序
     * @param carType
     * @param orderType
     * @param lon
     * @param lat
     * @param offset
     * @param pageSize
     * @return
     */
    List<Map<String,Object>> listOrdersByDistance(@Param("carType")String carType,@Param("orderType")Integer orderType,
    		@Param("lon")double lon,@Param("lat")double lat,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
}