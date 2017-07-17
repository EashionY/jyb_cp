package cn.jyb.dao;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.VerCode;

public interface VerCodeDao {

	/**
	 * ����ע����֤��
	 * @param phone
	 * @param regcode
	 * @return
	 */
	public int saveRegcode(@Param("phone")String phone,@Param("regcode")String regcode);
	
	/**
	 * ����ע����֤��
	 * @param phone
	 * @param regcode
	 * @return
	 */
	public int updateRegcode(@Param("phone")String phone,@Param("regcode")String regcode);
	
	public VerCode findByPhone(String phone);
	
	/**
	 * ���¸�����֤��
	 * @param phone
	 * @param pwdcode
	 * @return
	 */
	public int updatePwdcode(@Param("phone")String phone,@Param("pwdcode")String pwdcode);
	
	/**
	 * ����֧����֤��
	 * @param phone
	 * @param paycode
	 * @return
	 */
	public int updatePaycode(@Param("phone")String phone,@Param("paycode")String paycode);
}
