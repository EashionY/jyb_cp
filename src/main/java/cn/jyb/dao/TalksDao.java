package cn.jyb.dao;

import java.util.List;

import cn.jyb.entity.Talks;

public interface TalksDao {

	/**
	 * 保存朋友圈说说
	 * @param talks
	 * @return int
	 */
	public int save(Talks talks);
	
	/**
	 * 查找特定用户发表的说说
	 * @param user_id
	 * @return
	 */
	public List<Talks> listTalks(int user_id,int offset,int pageSize);
	
	/**
	 * 查找所有用户发表的说说
	 * @return
	 */
	public List<Talks> listAllTalks(int offset,int pageSize);
	
	/**
	 * 通过talk_id查找某一条说说
	 * @param talk_id
	 * @return
	 */
	public Talks findTalks(int talk_id);
	
	/**
	 * 删除说说
	 * @param talk_id
	 * @return
	 */
	public int deleteTalks(int talk_id);
}
