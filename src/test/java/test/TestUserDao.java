package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.jyb.dao.UserDao;
import cn.jyb.entity.Talks;
import cn.jyb.entity.User;

public class TestUserDao extends TestBase{

	private UserDao userDao;
	
	@Before
	public void init(){
		userDao = super.getContext().getBean("userDao", UserDao.class);
	}
	
	@Test
	public void test1(){
		User user = userDao.findByPhone("18728428022");
		System.out.println(user);
	}
	
	@Test
	public void test2(){
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		String phone = "18408229155";
		System.out.println(phone.matches(phoneRegex));
		String path = "apache-tomcat-7.0.67\\wtpwebapps\\jyb_cp\\upload\\cover_image.pngupload\\cover_image.pngupload\\cover_image.pngupload\\cover_image.pngupload\\cover_image.p";
		System.out.println(path.length());
		System.out.println(System.currentTimeMillis());
	}

	@Test
	public void test3(){
		List<String> list = new ArrayList<String>();
		list.add("Switch");
		list.add("playStation4");
		String[] paths = list.toArray(new String[list.size()]);
		paths = Arrays.copyOf(paths, 9);
		System.out.println(Arrays.toString(paths));
		Talks talks = new Talks();
		talks.setUser_id(123);
		talks.setTalk("123124aeq");
		talks.setImgpath1("123qewq");
		talks.setImgpath2(paths[1]);
		talks.setImgpath1(paths[2]);
		System.out.println(talks);
	}
	
	@Test
	public void test4(){
		User user = userDao.findById(14);
		System.out.println(user);
	}
} 
