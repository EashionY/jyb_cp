package cn.jyb.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface TalkService {

	/**
	 * 保存朋友圈说说
	 * @param user_id
	 * @param talk
	 * @param request
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean saveTalks(String phone,int user_id,String talk,HttpServletRequest request) throws IOException;

	/**
	 * 查找指定用户发表的说说
	 * @param user_id
	 * @return
	 */
	public List<Map<String, Object>> listTalks(int user_id,int page,int pageSize);
	
	/**
	 * 查找所有用户发表的说说
	 * @return
	 */
	public List<Map<String, Object>> listAllTalks(int currentUser_id,int page,int pageSize);
	
	/**
	 * 通过talk_id查看某一条说说的详情
	 * @param talk_id
	 * @return 
	 */
	public Map<String, Object> talksDetail(int talk_id,int currentUser_id);
	
	/**
	 * 删除说说
	 * @param talk_id
	 */
	public void deleteTalks(int talk_id);
	
}
