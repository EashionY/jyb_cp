package cn.jyb.util;

public class Version {
	//版本号
	private static final String VERSION = "1.0.3";
	//软件更新下载地址
	private static final String UPDATEURL = "http://a.app.qq.com/o/simple.jsp?pkgname=com.edriving";

	public static String getVersion() {
		return VERSION;
	}

	public static String getUpdateurl() {
		return UPDATEURL;
	}
	
}
