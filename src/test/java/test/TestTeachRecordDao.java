package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.jyb.dao.TeachRecordDao;
import cn.jyb.entity.TeachRecord;

public class TestTeachRecordDao extends TestBase {

	private TeachRecordDao trDao;
	
	@Before
	public void init(){
		trDao = super.getContext().getBean("teachRecordDao", TeachRecordDao.class);
	}
	
	@Test
	public void test1(){
		List<TeachRecord> list = trDao.findTeachEvaluation(21,0,5);
		System.out.println(list);
	}
	
	@Test
	/**
	 * ≤‚ ‘∑÷“≥≤È—Ø
	 */
	public void test2(){
		List<TeachRecord> list = trDao.findTeachEvaluations(21, 1, 6, 5);
		System.out.println(list);
	}
	
	@Test
	public void test3(){
		int n = trDao.findStudyEvaluationNumber(13);
		System.out.println(n);
	}
	
	@Test
	public void test4(){
		List<TeachRecord> list = trDao.findStudyEvaluation(13, 0, 5);
		System.out.println(list);
	}
}
