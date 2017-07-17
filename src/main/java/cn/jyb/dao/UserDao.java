package cn.jyb.dao;

import cn.jyb.entity.User;

public interface UserDao {

	/**
	 * 通过手机号查找用户
	 * @param phone
	 * @return
	 */
	public User findByPhone(String phone);
	
	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	public int save(User user);
	
	/**
	 * 修改密码
	 * @param phone
	 * @param newPassword
	 * @return
	 */
	public int modifyPassword(String phone,String newPassword);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int modifyUserinfo(User user);
	
	/**
	 * 通过用户id查找用户
	 * @param user_id
	 * @return String
	 */
	public User findById(int user_id);
	
	/**
	 * 上传头像成功之后保存头像路径
	 * @param imgpath
	 * @return int
	 */
	public int updateHeadImg(String imgpath,String phone);
}
