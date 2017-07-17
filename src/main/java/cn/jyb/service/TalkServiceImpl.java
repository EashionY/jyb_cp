package cn.jyb.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jyb.dao.CommentsDao;
import cn.jyb.dao.TalksDao;
import cn.jyb.dao.UserDao;
import cn.jyb.entity.Talks;
import cn.jyb.entity.User;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.NoTalksFoundException;
import cn.jyb.exception.NoUserFoundException;
import cn.jyb.util.Upload;
@Service("talkService")
@Transactional
public class TalkServiceImpl implements TalkService{

	@Resource
	private TalksDao talksDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private CommentsDao commentsDao;
	
	@Resource
	private CommentService commentService;
	
	public boolean saveTalks(String phone, int user_id, String talk, HttpServletRequest request) throws IOException {
		String folder = "talkimg";
		List<String> list = Upload.uploadImg(request, phone, folder);
		String[] paths = list.toArray(new String[list.size()]);
		paths = Arrays.copyOf(paths,9);
		User user = userDao.findById(user_id);
		if(user==null){
			throw new NoUserFoundException("用户不存在");
		}
        Talks talks = new Talks();
        talks.setUser_id(user_id);
        talks.setTalk(talk);
        talks.setImgpath1(paths[0]==null?"":paths[0]);
        talks.setImgpath2(paths[1]==null?"":paths[1]);
        talks.setImgpath3(paths[2]==null?"":paths[2]);
        talks.setImgpath4(paths[3]==null?"":paths[3]);
        talks.setImgpath5(paths[4]==null?"":paths[4]);
        talks.setImgpath6(paths[5]==null?"":paths[5]);
        talks.setImgpath7(paths[6]==null?"":paths[6]);
        talks.setImgpath8(paths[7]==null?"":paths[7]);
        talks.setImgpath9(paths[8]==null?"":paths[8]);
		try {
			talksDao.save(talks);
		} catch (Exception e) {
			throw new DataBaseException("保存说说失败");
		}
		return true;
	}

	public List<Map<String,Object>> listTalks(int user_id,int page,int pageSize) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		int offset = (page-1)*pageSize;
		List<Talks> talksList = talksDao.listTalks(user_id,offset,pageSize);
		if(talksList==null || talksList.isEmpty()){
			throw new NoTalksFoundException("未找到说说");
		}
		User user = userDao.findById(user_id);
		if(user==null){
			throw new NoUserFoundException("未找到相关用户");
		}
		String nickname = user.getNickname();
		String headImgpath = user.getImgpath();
		for(Talks talks : talksList){
			Map<String,Object> map = new HashMap<String, Object>();
			List<String> imgList = new ArrayList<String>();
			String creatime = sdf.format(talks.getCreatime());
			String imgpath1 = talks.getImgpath1();
			String imgpath2 = talks.getImgpath2();
			String imgpath3 = talks.getImgpath3();
			String imgpath4 = talks.getImgpath4();
			String imgpath5 = talks.getImgpath5();
			String imgpath6 = talks.getImgpath6();
			String imgpath7 = talks.getImgpath7();
			String imgpath8 = talks.getImgpath8();
			String imgpath9 = talks.getImgpath9();
			String talk = talks.getTalk();
			int talk_id = talks.getId();
			map.put("talk_id", ""+talk_id);
			int commentsNum = commentsDao.findCommentsNum(talk_id);
			map.put("commentsNum", ""+commentsNum);
			int likesNum = commentsDao.findLikesNum(talk_id);
			map.put("likesNum", ""+likesNum);
			String likes = commentsDao.likes(talk_id, user_id);
			if(likes==null || likes.trim().isEmpty()){
				likes = "0";
			}
			map.put("likes", likes);
			List<Map<String,String>> commentsList = commentService.listComments(talk_id);
			map.put("nickname", nickname);
			map.put("headImgpath", headImgpath);
			map.put("creatime", creatime);
			map.put("talk", talk);
			imgList.add(imgpath1);
			imgList.add(imgpath2);
			imgList.add(imgpath3);
			imgList.add(imgpath4);
			imgList.add(imgpath5);
			imgList.add(imgpath6);
			imgList.add(imgpath7);
			imgList.add(imgpath8);
			imgList.add(imgpath9);
			map.put("imgList", imgList);
			map.put("commentsList", commentsList);
			list.add(map);
		}
		return list;
	}

	public List<Map<String,Object>> listAllTalks(int currentUser_id,int page,int pageSize) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		int offset = (page-1)*pageSize;
		List<Talks> talksList = talksDao.listAllTalks(offset,pageSize);
		if(talksList==null || talksList.isEmpty()){
			throw new NoTalksFoundException("未找到说说");
		}
		for(Talks talks : talksList){
			Map<String,Object> map = new HashMap<String, Object>();
			List<String> imgList = new ArrayList<String>();
			int user_id = talks.getUser_id();
			User user = userDao.findById(user_id);
			if(user==null){
				throw new NoUserFoundException("未找到相关用户");
			}
			String nickname = user.getNickname();
			String headImgpath = user.getImgpath();
			String creatime = sdf.format(talks.getCreatime());
			String imgpath1 = talks.getImgpath1();
			String imgpath2 = talks.getImgpath2();
			String imgpath3 = talks.getImgpath3();
			String imgpath4 = talks.getImgpath4();
			String imgpath5 = talks.getImgpath5();
			String imgpath6 = talks.getImgpath6();
			String imgpath7 = talks.getImgpath7();
			String imgpath8 = talks.getImgpath8();
			String imgpath9 = talks.getImgpath9();
			String talk = talks.getTalk();
			int talk_id = talks.getId();
			map.put("talk_id", ""+talk_id);
			int commentsNum = commentsDao.findCommentsNum(talk_id);
			map.put("commentsNum", ""+commentsNum);
			int likesNum = commentsDao.findLikesNum(talk_id);
			map.put("likesNum", ""+likesNum);
			String likes = commentsDao.likes(talk_id, currentUser_id);
			if(likes==null || likes.trim().isEmpty()){
				likes = "0";
			}
			map.put("likes", likes);
			List<Map<String,String>> commentsList = commentService.listComments(talk_id);
//			System.out.println("评论列表:"+commentsList);
			map.put("nickname", nickname);
			map.put("headImgpath", headImgpath);
			map.put("creatime", creatime);
			map.put("talk", talk);
			imgList.add(imgpath1);
			imgList.add(imgpath2);
			imgList.add(imgpath3);
			imgList.add(imgpath4);
			imgList.add(imgpath5);
			imgList.add(imgpath6);
			imgList.add(imgpath7);
			imgList.add(imgpath8);
			imgList.add(imgpath9);
			map.put("imgList", imgList);
			map.put("commentsList", commentsList);
			list.add(map);
		}
		return list;
	}

	public Map<String,Object> talksDetail(int talk_id,int currentUser_id) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		Talks talks = talksDao.findTalks(talk_id);
		if(talks==null){
			throw new NoTalksFoundException("未找到对应说说");
		}
		int user_id = talks.getUser_id();
		User user = userDao.findById(user_id);
		if(user==null){
			throw new NoUserFoundException("未找到相关用户");
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nickname",user.getNickname());
		map.put("headImgpath",user.getImgpath());
		map.put("talk", talks.getTalk());
		map.put("creatime", sdf.format(talks.getCreatime()));
		map.put("talk_id", ""+talks.getId());
		int commentsNum = commentsDao.findCommentsNum(talk_id);
		map.put("commentsNum", ""+commentsNum);
		int likesNum = commentsDao.findLikesNum(talk_id);
		map.put("likesNum", ""+likesNum);
		String likes = commentsDao.likes(talk_id, currentUser_id);
		if(likes==null || likes.trim().isEmpty()){
			likes = "0";
		}
		map.put("likes", likes);
		List<String> imgList = new ArrayList<String>();
		imgList.add(talks.getImgpath1());
		imgList.add(talks.getImgpath2());
		imgList.add(talks.getImgpath3());
		imgList.add(talks.getImgpath4());
		imgList.add(talks.getImgpath5());
		imgList.add(talks.getImgpath6());
		imgList.add(talks.getImgpath7());
		imgList.add(talks.getImgpath8());
		imgList.add(talks.getImgpath9());
		map.put("imgList", imgList);
		List<Map<String,String>> commentsList = commentService.listComments(talk_id);
		map.put("commentsList",commentsList);
		return map;
	}

	public void deleteTalks(int talk_id) {
		try {
			talksDao.deleteTalks(talk_id);
			commentsDao.deleteComments(talk_id);
		} catch (Exception e) {
			throw new DataBaseException("删除说说失败");
		}
	}
	
	
	
}
