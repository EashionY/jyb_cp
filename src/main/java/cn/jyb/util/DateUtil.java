package cn.jyb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateUtil {

	 /** ������ʱ����(���»���) yyyyMMddHHmmss */
    public static final String dtLong = "yyyyMMddHHmmss";
    
    /** ����ʱ�� yyyy-MM-dd HH:mm:ss */
    public static final String simple = "yyyy-MM-dd HH:mm:ss";
    
    /** ������(���»���) yyyyMMdd */
    public static final String dtShort = "yyyyMMdd";
	
    /**
     * ����ϵͳ��ǰʱ��(��ȷ������),��Ϊһ��Ψһ�Ķ������
     * @return
     *      ��yyyyMMddHHmmssΪ��ʽ�ĵ�ǰϵͳʱ��
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * ��ȡϵͳ��ǰ����(��ȷ������)����ʽ��yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * ��ȡϵͳ����������(��ȷ����)����ʽ��yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * �����������λ��
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		String str = rad.nextInt(1000)+"";
		if(str.length()==1){
			str = "00"+str;
		}else if(str.length()==2){
			str = "0"+str;
		}
		return str;
	}
}
