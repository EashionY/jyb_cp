package cn.jyb.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

	/**
	 * 教练设置日程表
	 * @param data 三天内的日程安排
	 * @return boolean
	 * @throws ParseException 
	 */
	public boolean setCoachSchedule(String data);
	
	/**
	 * 列出教练最近三天的日程表供学员查看
	 * @param data
	 * @throws ParseException
	 */
	public List<Map<String,String>> listCoachSchedule(String data);
	
	/**
	 * 教练查看自己最近三天的日程安排
	 * @param data
	 * @return
	 */
	public List<Map<String,String>> checkCoachSchedule(String data);
	
	/**
	 * 获取全部教练的日程(后台)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllCoachSchedule(int page,int pageSize);
	
	/**
	 * 通过教练名查找日程
	 * @param coach_name
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByName(String coach_name);
	
	/**
	 * 通过预约日期查找日程
	 * @param appoint_time
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByDate(String appoint_time);
}
