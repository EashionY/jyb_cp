package cn.jyb.service;

import java.util.List;
import java.util.Map;

public interface CommentService {
	
	/**
	 * ������Ȧ��˵˵�������
	 * @param talk_id
	 * @param user_id
	 * @param comment
	 * @return map
	 */
	public Map<String, Object> addComments(int talk_id,int user_id,String comment);
	
	/**
	 * ����
	 * @param talk_id
	 * @param user_id
	 * @return
	 */
	public boolean addLikes(int talk_id,int user_id);
	
	/**
	 * ���Ҹ���˵˵����������
	 * @param talk_id
	 * @return
	 */
	public List<Map<String, String>> listComments(int talk_id);
	
}
