package cn.jyb.dao;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.User;

public interface UserDao {

	/**
	 * ͨ���ֻ��Ų����û�
	 * @param phone
	 * @return
	 */
	public User findByPhone(String phone);
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @return
	 */
	public int save(User user);
	
	/**
	 * �޸�����
	 * @param phone
	 * @param newPassword
	 * @return
	 */
	public int modifyPassword(@Param("phone")String phone,@Param("newPassword")String newPassword);
	
	/**
	 * �޸��û���Ϣ
	 * @param user
	 * @return
	 */
	public int modifyUserinfo(User user);
	
	/**
	 * ͨ���û�id�����û�
	 * @param user_id
	 * @return String
	 */
	public User findById(int user_id);
	
	/**
	 * �ϴ�ͷ��ɹ�֮�󱣴�ͷ��·��
	 * @param imgpath
	 * @return int
	 */
	public int updateHeadImg(String imgpath,String phone);
	
	/**
	 * �л��ͻ��˽�ɫ
	 * @param user_id
	 * @param role 0:ѧԱ��1:������2:����
	 * @return
	 */
	public int changeRole(@Param("user_id")Integer user_id,@Param("role")String role);
	
	/**
	 * �û������ֻ���
	 * @param user_id
	 * @param newPhone
	 * @return
	 */
	public int changePhone(@Param("user_id")Integer user_id,@Param("newPhone")String newPhone);
	
}
