package cn.jyb.dao;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.VerCode;

public interface VerCodeDao {

	/**
	 * 保存注册验证码
	 * @param phone
	 * @param regcode
	 * @return
	 */
	public int saveRegcode(@Param("phone")String phone,@Param("regcode")String regcode);
	
	/**
	 * 更新注册验证码
	 * @param phone
	 * @param regcode
	 * @return
	 */
	public int updateRegcode(@Param("phone")String phone,@Param("regcode")String regcode);
	
	public VerCode findByPhone(String phone);
	
	/**
	 * 更新改密验证码
	 * @param phone
	 * @param pwdcode
	 * @return
	 */
	public int updatePwdcode(@Param("phone")String phone,@Param("pwdcode")String pwdcode);
	
	/**
	 * 更新支付验证码
	 * @param phone
	 * @param paycode
	 * @return
	 */
	public int updatePaycode(@Param("phone")String phone,@Param("paycode")String paycode);
}
