package cn.jyb.util;

import java.security.MessageDigest;
import java.util.Random;

import org.apache.tomcat.util.codec.binary.Base64;

public class AccountUtil {
	/**
	 * 使用MD5对字符串进行加密（密码加密）
	 * @param src
	 * @return
	 */
	public static String md5(String src){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(src.getBytes());
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (Exception e) {
			throw new MD5Exception("加密失败",e);
		}
	}
	
	/**
	 * 生成6位推荐码
	 * @return
	 */
	public static String getRec(){
		//所有候选组成验证码的字符
        String[] recCodeArrary={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
               "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
               "A","B","C","D","E","F","G","H","I","J", "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
               };
        StringBuilder recCode = new StringBuilder();
        Random random = new Random();
        //利用一定范围内的随机数做为验证码数组的下标，循环组成我们需要长度的验证码
        for(int i=0;i<6;i++){
        	recCode.append(recCodeArrary[random.nextInt(recCodeArrary.length)]);
    	}
        return recCode.toString();
	}

	
}
