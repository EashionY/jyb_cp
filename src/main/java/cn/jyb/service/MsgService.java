package cn.jyb.service;
/**
 * ������֤��
 * @author Eashion
 *
 */
public interface MsgService {
	
	/**
	 * ����ע����֤��
	 * @param phone
	 * @return
	 */
	public String sendRegCode(String phone);
	
	/**
	 * ���͸�����֤��
	 * @param phone
	 * @return
	 */
	public String sendPwdCode(String phone);

	/**
	 * ����֧����֤��
	 * @param phone
	 * @return
	 */
	public String sendPayCode(String phone);
}
