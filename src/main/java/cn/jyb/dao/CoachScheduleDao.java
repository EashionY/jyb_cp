package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.CoachSchedule;

public interface CoachScheduleDao {

	/**
	 * �������ԤԼʱ���ճ�
	 * @param coachSchedule
	 * @return int
	 */
	public int saveCoachSchedule(CoachSchedule coachSchedule);
	
	/**
	 * �����޸�ԤԼʱ���ճ�
	 * @param coachSchedule
	 * @return int
	 */
	public int updateCoachSchedule(CoachSchedule coachSchedule);
	
	/**
	 * ͨ��ԤԼ���ڲ����ճ�
	 * @param appoint_time
	 * @param coach_id
	 * @return coachSchedule
	 */
	public CoachSchedule findSchedule(String appoint_time,int coach_id);
	
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
	 * ͨ�������������ճ�
	 * @param coach_name
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByName(String coach_name);
	
	/**
	 * ͨ��ԤԼ���ڲ����ճ�
	 * @param appoint_time
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByDate(String appoint_time);
	
	
}
