package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Comments;

public interface CommentsDao {

	/**
	 * 添加评论
	 * @param comments
	 * @return
	 */
	public int save(Comments comments);
	
	/**
	 * 点赞
	 * @return
	 */
	public int addLikes(@Param("talk_id")int talk_id, @Param("user_id")int user_id);
	
	/**
	 * 查看当前用户对某条说说的点赞情况
	 * @param talk_id 说说id
	 * @param user_id 当前用户id
	 * @return 1已点赞，0未点赞
	 */
	public String likes(@Param("talk_id")int talk_id, @Param("user_id")int user_id);
	
	/**
	 * 查找某条说说的评论数
	 * @param talk_id
	 * @return
	 */
	public int findCommentsNum(int talk_id);
	
	/**
	 * 查找某条说说的点赞数
	 * @param talk_id
	 * @return
	 */
	public int findLikesNum(int talk_id);
	
	/**
	 * 查找全部评论
	 * @param talk_id
	 * @param user_id
	 * @return
	 */
	public List<Comments> listComments(int talk_id);

	/**
	 * 删除该条说说对应的评论
	 * @param talk_id
	 */
	public void deleteComments(int talk_id);
	
}
