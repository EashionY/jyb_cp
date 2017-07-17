package test;

import org.junit.Before;
import org.junit.Test;

import cn.jyb.dao.TalksDao;
import cn.jyb.entity.Talks;

public class TestTalksDao extends TestBase {

	private TalksDao tkDao;
	
	@Before
	public void init(){
		tkDao = super.getContext().getBean("talksDao", TalksDao.class);
	}
	
	@Test
	public void testSave(){
		Talks talks = new Talks();
		talks.setUser_id(2);
		talks.setTalk("为什么走不通");
		talks.setImgpath1("12321/qwead/2323");
		int i = tkDao.save(talks);
		System.out.println(i);
	}
	
}
