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
	
	/**
	 * ���ͱ���ֻ�����֤��
	 * @param phone
	 * @return
	 */
	public String sendPhoneCode(String phone);
	
	/**
	 * ����һ��Ų������
	 * @param userId Ҫ��Ų�����û�id
	 * @param moveUserId Ų���˵��û�id
	 * @param vehicleNo ���ƺ�
	 * @return
	 */
	public boolean sendMoveCarMsg(Integer userId,Integer moveUserId,String vehicleNo);
	
	
}
