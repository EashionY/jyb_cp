package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Comments;

public interface CommentsDao {

	/**
	 * �������
	 * @param comments
	 * @return
	 */
	public int save(Comments comments);
	
	/**
	 * ����
	 * @return
	 */
	public int addLikes(@Param("talk_id")int talk_id, @Param("user_id")int user_id);
	
	/**
	 * �鿴��ǰ�û���ĳ��˵˵�ĵ������
	 * @param talk_id ˵˵id
	 * @param user_id ��ǰ�û�id
	 * @return 1�ѵ��ޣ�0δ����
	 */
	public String likes(@Param("talk_id")int talk_id, @Param("user_id")int user_id);
	
	/**
	 * ����ĳ��˵˵��������
	 * @param talk_id
	 * @return
	 */
	public int findCommentsNum(int talk_id);
	
	/**
	 * ����ĳ��˵˵�ĵ�����
	 * @param talk_id
	 * @return
	 */
	public int findLikesNum(int talk_id);
	
	/**
	 * ����ȫ������
	 * @param talk_id
	 * @param user_id
	 * @return
	 */
	public List<Comments> listComments(int talk_id);

	/**
	 * ɾ������˵˵��Ӧ������
	 * @param talk_id
	 */
	public void deleteComments(int talk_id);
	
}
