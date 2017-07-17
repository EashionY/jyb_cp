package cn.jyb.dao;

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
	public int modifyPassword(String phone,String newPassword);
	
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
}
