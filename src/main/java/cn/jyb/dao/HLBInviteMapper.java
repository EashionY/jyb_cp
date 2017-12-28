package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.HLBInvite;

public interface HLBInviteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HLBInvite record);

    int insertSelective(HLBInvite record);

    HLBInvite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HLBInvite record);
	
    int updateByPrimaryKey(HLBInvite record);
    /**
     * �鿴������ӵ��б�
     * @param invited
     * @return
     */
    List<Map<String,Object>> getInvites(@Param("invited")Integer invited,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    
    HLBInvite findInvite(@Param("hlbOrderNo")String hlbOrderNo,@Param("invited")Integer invited);
    /**
     * ɾ���ö��������������¼
     * @param hlbOrderNo
     * @return
     */
    int deleteByHlbOrderNo(String hlbOrderNo);
}