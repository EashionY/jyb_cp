package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.CoachSchedule;

public interface CoachScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CoachSchedule record);

    int insertSelective(CoachSchedule record);

    CoachSchedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoachSchedule record);

    int updateByPrimaryKey(CoachSchedule record);
    
    /**
	 * ͨ��ԤԼ���ڲ����ճ�
	 * @param appointTime
	 * @param coachId
	 * @return coachSchedule
	 */
	public CoachSchedule findSchedule(String appointTime,Integer coachId);
	
	/**
	 * �г����н������ճ̰���
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllCoachSchedule(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * ��ȡ�����ճ̱��������
	 * @return 
	 */
	public int getCoachScheduleNum();
	
	/**
	 * ͨ��ԤԼ���ڲ����ճ�
	 * @param appointTime
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByDate(String appointTime);
	
	/**
	 * ͨ�������������ճ�
	 * @param coachName
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByName(String coachName);
}