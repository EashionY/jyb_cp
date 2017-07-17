package cn.jyb.util;

public class Version {
	//版本号
	private static final String VERSION = "1.0.1";
	//软件更新下载地址
	private static final String UPDATEURL = "http://shouji.baidu.com/software/11840081.html";

	public static String getVersion() {
		return VERSION;
	}

	public static String getUpdateurl() {
		return UPDATEURL;
	}
	
}
