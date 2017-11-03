package cn.jyb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

/**
 * 环信的网络请求工具类
 * @author Eashion
 *
 */
public class EasemobHttp {
	
	/**
	 * 获取token
	 * @return
	 */
	public static String getToken(){
		String token = EasemobUtil.getFromCache();
//      System.out.println("1st token:"+token);
        if(token == null){
      	    token = EasemobUtil.getNew();
//      	System.out.println("2nd token:"+token);
        }
        return token;
	}
	
	/**
	 * 发送GET请求
	 * @param url
	 * @return
	 */
	public static JSONObject get(String url){
        HttpURLConnection http = null;
        InputStream in = null;
        String token = getToken();
        try {
            URL urlGet = new URL(url);
            http = (HttpURLConnection) urlGet.openConnection();
            //设置请求方式
            http.setRequestMethod("GET");
            //设置请求头
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("Authorization", "Bearer "+token);
            http.setDoOutput(true);
            http.setDoInput(true);

            http.connect();

            in =http.getInputStream();
            int size =in.available();
            byte[] jsonBytes =new byte[size];
            in.read(jsonBytes);
            String message=new String(jsonBytes,"UTF-8");
            return JSONObject.fromObject(message);
        } catch (Exception e) {
            return null;
        }finally {
            if(null != http) {
            	http.disconnect();
            }
            try {
                if (null != in) { 
                	in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

	/**
	 * 发送POST请求
	 * @param url
	 * @param data
	 * @return
	 */
    public static JSONObject post(String url,String data){
        HttpURLConnection http = null;
        PrintWriter out = null;
        BufferedReader reader = null;
        try {
            //创建连接
            URL urlPost = new URL(url);
            http = (HttpURLConnection) urlPost.openConnection();
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setRequestMethod("POST");
            http.setUseCaches(false);
            http.setInstanceFollowRedirects(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");

            http.connect();

            //POST请求
            OutputStreamWriter outWriter = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            out = new PrintWriter(outWriter);
            out.print(data);
            out.flush();
            out.close();
            out = null;

            //读取响应
            reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            reader = null;
            System.out.println(sb.toString());
            return JSONObject.fromObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if(null != http) {
            	http.disconnect();
            }
            if(null != out) {
            	out.close();
            }
            try{
                if(null != reader) {
                	reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 带有授权信息的POST请求
     * @param url
     * @param data body数据
     * @return
     */
    public static JSONObject authPost(String url,String data){
        HttpURLConnection http = null;
        PrintWriter out = null;
        BufferedReader reader = null;
        String token = getToken();
        try {
            //创建连接
            URL urlPost = new URL(url);
            http = (HttpURLConnection) urlPost.openConnection();
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setRequestMethod("POST");
            http.setUseCaches(false);
            http.setInstanceFollowRedirects(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("Authorization", "Bearer "+token);

            http.connect();

            //POST请求
            OutputStreamWriter outWriter = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            out = new PrintWriter(outWriter);
            out.print(data);
            out.flush();
            out.close();
            out = null;

            //读取响应
            reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            reader = null;
            System.out.println(sb.toString());
            return JSONObject.fromObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if(null != http) {
            	http.disconnect();
            }
            if(null != out) {
            	out.close();
            }
            try{
                if(null != reader) {
                	reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    
    /**
	 * 发送DELETE请求
	 * @param url
	 * @return
	 */
	public static JSONObject delete(String url){
        HttpURLConnection http = null;
        InputStream in = null;
        String token = getToken();
        try {
            URL urlGet = new URL(url);
            http = (HttpURLConnection) urlGet.openConnection();
            //设置请求方式
            http.setRequestMethod("DELETE");
            //设置请求头
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("Authorization", "Bearer "+token);
            http.setDoOutput(true);
            http.setDoInput(true);

            http.connect();

            in =http.getInputStream();
            int size =in.available();
            byte[] jsonBytes =new byte[size];
            in.read(jsonBytes);
            String message=new String(jsonBytes,"UTF-8");
            return JSONObject.fromObject(message);
        } catch (Exception e) {
            return null;
        }finally {
            if(null != http) {
            	http.disconnect();
            }
            try {
                if (null != in) { 
                	in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
	
	/**
     * 发送PUT请求
     * @param url
     * @param data body数据
     * @return
     */
    public static JSONObject put(String url,String data){
        HttpURLConnection http = null;
        PrintWriter out = null;
        BufferedReader reader = null;
        String token = getToken();
        try {
            //创建连接
            URL urlPost = new URL(url);
            http = (HttpURLConnection) urlPost.openConnection();
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setRequestMethod("PUT");
            http.setUseCaches(false);
            http.setInstanceFollowRedirects(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("Authorization", "Bearer "+token);

            http.connect();

            //PUT请求
            OutputStreamWriter outWriter = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            out = new PrintWriter(outWriter);
            out.print(data);
            out.flush();
            out.close();
            out = null;

            //读取响应
            reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            reader = null;
            System.out.println(sb.toString());
            return JSONObject.fromObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if(null != http) {
            	http.disconnect();
            }
            if(null != out) {
            	out.close();
            }
            try{
                if(null != reader) {
                	reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
	
}
