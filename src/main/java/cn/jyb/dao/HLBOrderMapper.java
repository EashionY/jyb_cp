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
    
    Double getDriverScore(Integer receiptId);//��������
    
    int getDriverOrderNum(Integer receiptId);//�����ѽӶ�������
    
    Double getPassengerScore(Integer publishId);//�˿�����ֵ
    
    int getPassengerOrderNum(Integer publishId);//�˿���ɵĶ�����
    
    int getOrderNum(@Param("carType")String carType,@Param("orderType")Integer orderType);//��ȡ������
    /**
     * �����б�����������
     * @param carType
     * @param orderType
     * @param price 1/0���۸�����
     * @param offset
     * @param pageSize
     * @return
     */
    List<Map<String,Object>> listOrders(@Param("carType")String carType,@Param("orderType")Integer orderType,
    		@Param("lon")double lon,@Param("lat")double lat,@Param("price")String price,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    /**
     * ���������������б����վ�������
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