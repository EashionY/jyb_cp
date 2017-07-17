package test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import cn.jyb.dao.CoachDao;

public class TestCoachDao extends TestBase {

	@Resource
	private CoachDao cDao;
	
	@Before
	public void init(){
		cDao = super.getContext().getBean("coachDao", CoachDao.class);
	}
	
	@Test
	public void test(){
		cDao.updateCoachBrowse("1");
		
	}
	
	@Test
	public void test2(){
		String coach_name = "%"+"Íõ"+"%";
		List<Map<String, Object>> list = cDao.findCoachByName(coach_name);
		System.out.println(list);
	}
}
