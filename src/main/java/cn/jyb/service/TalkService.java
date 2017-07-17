package cn.jyb.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface TalkService {

	/**
	 * ��������Ȧ˵˵
	 * @param user_id
	 * @param talk
	 * @param request
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean saveTalks(String phone,int user_id,String talk,HttpServletRequest request) throws IOException;

	/**
	 * ����ָ���û������˵˵
	 * @param user_id
	 * @return
	 */
	public List<Map<String, Object>> listTalks(int user_id,int page,int pageSize);
	
	/**
	 * ���������û������˵˵
	 * @return
	 */
	public List<Map<String, Object>> listAllTalks(int currentUser_id,int page,int pageSize);
	
	/**
	 * ͨ��talk_id�鿴ĳһ��˵˵������
	 * @param talk_id
	 * @return 
	 */
	public Map<String, Object> talksDetail(int talk_id,int currentUser_id);
	
	/**
	 * ɾ��˵˵
	 * @param talk_id
	 */
	public void deleteTalks(int talk_id);
	
}
