package cn.jyb.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

	/**
	 * ���������ճ̱�
	 * @param data �����ڵ��ճ̰���
	 * @return boolean
	 * @throws ParseException 
	 */
	public boolean setCoachSchedule(String data);
	
	/**
	 * �г��������������ճ̱�ѧԱ�鿴
	 * @param data
	 * @throws ParseException
	 */
	public List<Map<String,String>> listCoachSchedule(String data);
	
	/**
	 * �����鿴�Լ����������ճ̰���
	 * @param data
	 * @return
	 */
	public List<Map<String,String>> checkCoachSchedule(String data);
	
	/**
	 * ��ȡȫ���������ճ�(��̨)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllCoachSchedule(int page,int pageSize);
	
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
