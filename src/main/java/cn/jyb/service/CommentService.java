package cn.jyb.service;

import java.util.List;
import java.util.Map;

public interface CommentService {
	
	/**
	 * 对朋友圈的说说添加评价
	 * @param talk_id
	 * @param user_id
	 * @param comment
	 * @return map
	 */
	public Map<String, Object> addComments(int talk_id,int user_id,String comment);
	
	/**
	 * 点赞
	 * @param talk_id
	 * @param user_id
	 * @return
	 */
	public boolean addLikes(int talk_id,int user_id);
	
	/**
	 * 查找该条说说的所有评论
	 * @param talk_id
	 * @return
	 */
	public List<Map<String, String>> listComments(int talk_id);
	
}
