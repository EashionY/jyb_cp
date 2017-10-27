package cn.jyb.dao;

import org.apache.ibatis.annotations.Param;

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
	public int modifyPassword(@Param("phone")String phone,@Param("newPassword")String newPassword);
	
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
	
	/**
	 * 切换客户端角色
	 * @param user_id
	 * @param role 0:学员，1:教练，2:车主
	 * @return
	 */
	public int changeRole(@Param("user_id")Integer user_id,@Param("role")String role);
	
	/**
	 * 用户更换手机号
	 * @param user_id
	 * @param newPhone
	 * @return
	 */
	public int changePhone(@Param("user_id")Integer user_id,@Param("newPhone")String newPhone);
	
}
