package cn.jyb.util;
/**
 * ���ױ�app�汾
 * @author Eashion
 *
 */
public class Version {
	//�汾��
	private static final String VERSION = "1.0.3";
	//����������ص�ַ
	private static final String UPDATEURL = "http://a.app.qq.com/o/simple.jsp?pkgname=com.edriving";

	public static String getVersion() {
		return VERSION;
	}

	public static String getUpdateurl() {
		return UPDATEURL;
	}
	
}
