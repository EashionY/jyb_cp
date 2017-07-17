package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.CommentsDao;
import cn.jyb.dao.TalksDao;
import cn.jyb.dao.UserDao;
import cn.jyb.entity.Comments;
import cn.jyb.entity.Talks;
import cn.jyb.entity.User;
import cn.jyb.exception.CommentsException;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.NoUserFoundException;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentsDao commentsDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private TalksDao talksDao;
	
	public Map<String,Object> addComments(int talk_id, int user_id, String comment) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		if(comment==null || comment.trim().isEmpty()){
			throw new CommentsException("评论不能为空");
		}
		try {
			comment = URLDecoder.decode(comment, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Comments comments = new Comments();
		comments.setTalk_id(talk_id);
		comments.setUser_id(user_id);
		comments.setComment(comment);
		try {
			commentsDao.save(comments);
		} catch (Exception e) {
			throw new DataBaseException("添加评论失败");
		}
		User user = userDao.findById(user_id);
		if(user==null){
			throw new NoUserFoundException("未找到相关用户");
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nickname",user.getNickname());
		map.put("headImgpath",user.getImgpath());
		Talks talks = talksDao.findTalks(talk_id);
		map.put("talk", talks.getTalk());
		map.put("creatime", sdf.format(talks.getCreatime()));
		map.put("talk_id", ""+talks.getId());
		int commentsNum = commentsDao.findCommentsNum(talk_id);
		map.put("commentsNum", ""+commentsNum);
		int likesNum = commentsDao.findLikesNum(talk_id);
		map.put("likesNum", ""+likesNum);
		String likes = commentsDao.likes(talk_id, user_id);
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
		List<Map<String,String>> commentsList = listComments(talk_id);
		map.put("commentsList",commentsList);
		return map;
	}

	public boolean addLikes(int talk_id, int user_id) {
		String likes = commentsDao.likes(talk_id, user_id);
		if(likes==null || likes.trim().isEmpty()){
			try {
				commentsDao.addLikes(talk_id, user_id);
			} catch (Exception e) {
				throw new DataBaseException("点赞失败");
			}
			return true;
		}else{
			throw new DataBaseException("已点过赞了，请勿重复操作");
		}
	}

	public List<Map<String,String>> listComments(int talk_id) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		try {
			List<Comments> commentsList = commentsDao.listComments(talk_id);
			for(Comments comments : commentsList){
				Map<String,String> map = new HashMap<String,String>();
				int user_id = comments.getUser_id();
				User user = userDao.findById(user_id);
				String nickname = user.getNickname();
				String headImgpath = user.getImgpath();
				String comment = comments.getComment();
				String creatime = sdf.format(comments.getCreatime());
				map.put("nickname", nickname);
				map.put("headImgpath", headImgpath);
				map.put("comment", comment);
				map.put("creatime", creatime);
				list.add(map);
			}
		} catch (Exception e) {
			throw new DataBaseException("数据库连接失败");
		}
		return list;
	}

	
}
