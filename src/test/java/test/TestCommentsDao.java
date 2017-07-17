package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.jyb.dao.CommentsDao;
import cn.jyb.entity.Comments;

public class TestCommentsDao extends TestBase {

	private CommentsDao cmDao;
	
	@Before
	public void init(){
		cmDao = super.getContext().getBean("commentsDao", CommentsDao.class);
	}
	
	@Test
	public void testSave(){
		Comments cm = new Comments();
		cm.setTalk_id(2);
		cm.setUser_id(5);
		cm.setComment("°Ë¸ÂÑ½Â·");
		int i = cmDao.save(cm);
		System.out.println(i);
	}
	
	@Test
	public void testFindCommentsNum(){
		int n = cmDao.findCommentsNum(5);
		System.out.println(n);
	}
	
	@Test
	public void testListComments(){
		List<Comments> list = cmDao.listComments(2);
		System.out.println(list);
	}
	
	@Test
	public void testAddLikes(){
		int i = cmDao.addLikes(2,5);
		System.out.println(i);
	}
	
	@Test
	public void testLikes(){
		String likes = cmDao.likes(9, 15);
		System.out.println(likes);
	}
}
