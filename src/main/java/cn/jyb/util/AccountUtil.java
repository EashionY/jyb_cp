package cn.jyb.util;

import java.security.MessageDigest;
import java.util.Random;

import org.apache.tomcat.util.codec.binary.Base64;

public class AccountUtil {
	/**
	 * ʹ��MD5���ַ������м��ܣ�������ܣ�
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
			throw new MD5Exception("����ʧ��",e);
		}
	}
	
	/**
	 * ����6λ�Ƽ���
	 * @return
	 */
	public static String getRec(){
		//���к�ѡ�����֤����ַ�
        String[] recCodeArrary={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
               "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
               "A","B","C","D","E","F","G","H","I","J", "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
               };
        StringBuilder recCode = new StringBuilder();
        Random random = new Random();
        //����һ����Χ�ڵ��������Ϊ��֤��������±꣬ѭ�����������Ҫ���ȵ���֤��
        for(int i=0;i<6;i++){
        	recCode.append(recCodeArrary[random.nextInt(recCodeArrary.length)]);
    	}
        return recCode.toString();
	}

	
}
