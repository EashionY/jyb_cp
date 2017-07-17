package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.jyb.service.ScheduleService;

public class TestScheduleService extends TestBase {

	private ScheduleService scheduleService;
	
	@Before
	public void init(){
		scheduleService = super.getContext().getBean("scheduleService", ScheduleService.class);
	}
	
	@Test
	public void test1(){
		System.out.println(scheduleService.getClass().getName());
	}
	
	@Test
	public void testSetCoachSchedule(){
		List<Map<String,String>> params = new ArrayList<Map<String,String>>();
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("coach_id", "21");
		map1.put("appoint_time", "2017-05-09");
		map1.put("time1","-1");
		map1.put("time2","0");
		map1.put("time3","0");
		map1.put("time4","0");
		map1.put("time5","-1");
		map1.put("time6","-1");
		map1.put("time7","0");
		map1.put("time8","0");
		map1.put("time9","-1");
		map1.put("time10","-1");
		params.add(map1);
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("coach_id", "21");
		map2.put("appoint_time", "2017-05-10");
		map2.put("time1","-1");
		map2.put("time2","-1");
		map2.put("time3","0");
		map2.put("time4","0");
		map2.put("time5","0");
		map2.put("time6","-1");
		map2.put("time7","0");
		map2.put("time8","0");
		map2.put("time9","0");
		map2.put("time10","-1");
		params.add(map2);
		Map<String,String> map3 = new HashMap<String,String>();
		map3.put("coach_id", "21");
		map3.put("appoint_time", "2017-05-11");
		map3.put("time1","-1");
		map3.put("time2","-1");
		map3.put("time3","0");
		map3.put("time4","0");
		map3.put("time5","-1");
		map3.put("time6","-1");
		map3.put("time7","-1");
		map3.put("time8","0");
		map3.put("time9","-1");
		map3.put("time10","-1");
		params.add(map3);
		String data = "[{\"coach_id\":\"21\",\"appoint_time\":\"2017-05-11\","
				+ "\"time1\":\"-1\",\"time2\":\"-1\",\"time3\":\"0\",\"time4\":\"0\",\"time5\":\"-1\","
				+ "\"time6\":\"0\",\"time7\":\"0\",\"time8\":\"-1\",\"time9\":\"-1\",\"time10\":\"-1\"},"
				+ "{\"coach_id\":\"21\",\"appoint_time\":\"2017-05-12\","
				+ "\"time1\":\"-1\",\"time2\":\"-1\",\"time3\":\"0\",\"time4\":\"0\",\"time5\":\"-1\","
				+ "\"time6\":\"0\",\"time7\":\"0\",\"time8\":\"-1\",\"time9\":\"-1\",\"time10\":\"-1\"},"
				+ "{\"coach_id\":\"21\",\"appoint_time\":\"2017-05-13\","
				+ "\"time1\":\"-1\",\"time2\":\"-1\",\"time3\":\"0\",\"time4\":\"0\",\"time5\":\"-1\","
				+ "\"time6\":\"0\",\"time7\":\"0\",\"time8\":\"-1\",\"time9\":\"-1\",\"time10\":\"-1\"}]";
		boolean tf = scheduleService.setCoachSchedule(data);
		System.out.println(tf);
	}
}
