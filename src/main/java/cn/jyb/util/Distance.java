package cn.jyb.util;

public class Distance {

	
	private static double EARTH_RADIUS = 6378.137; 

	private static double rad(double d) { 
		return d * Math.PI / 180.0; 
	}
	
	/**
	 * 获取距离
	 * @param lon1 起点经度
	 * @param lat1 起点纬度
	 * @param lon2 终点经度
	 * @param lat2 终点纬度
	 * @return 距离
	 */
	public static String getDistance(double lon1,double lat1,double lon2,double lat2){
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;//纬度之差
		double b = rad(lon1) - rad(lon2);//经度之差
		double s = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))); 
		s = s * EARTH_RADIUS;
		//转换成String
		String dis = null;
		String sstr = s+"";
		//保留小数点后1位
		sstr = sstr.substring(0,sstr.indexOf(".")+2);
		dis=sstr+"km";
		return dis;
	}
	
	
}
