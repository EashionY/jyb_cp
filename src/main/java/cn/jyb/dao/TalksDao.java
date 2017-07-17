package cn.jyb.dao;

import java.util.List;

import cn.jyb.entity.Talks;

public interface TalksDao {

	/**
	 * ��������Ȧ˵˵
	 * @param talks
	 * @return int
	 */
	public int save(Talks talks);
	
	/**
	 * �����ض��û������˵˵
	 * @param user_id
	 * @return
	 */
	public List<Talks> listTalks(int user_id,int offset,int pageSize);
	
	/**
	 * ���������û������˵˵
	 * @return
	 */
	public List<Talks> listAllTalks(int offset,int pageSize);
	
	/**
	 * ͨ��talk_id����ĳһ��˵˵
	 * @param talk_id
	 * @return
	 */
	public Talks findTalks(int talk_id);
	
	/**
	 * ɾ��˵˵
	 * @param talk_id
	 * @return
	 */
	public int deleteTalks(int talk_id);
}
