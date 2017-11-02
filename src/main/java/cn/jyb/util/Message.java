package cn.jyb.util;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * ��ָ���ֻ����Ͷ���
 * @author Eashion
 *
 */
public class Message {

	//��֤�����ʱ�䣨���ӣ�
	private final static Integer time = 10;
	//����URL
	private final static String URL = "http://gw.api.taobao.com/router/rest";
	//����appkey��appsecret
	private final static String APPKEY = "23877667";
	private final static String SECRET = "92e470e2ef38917f516f9770ea845d0f";
	
	/**
	 * ������֤��
	 * @param phone
	 * @param verCode
	 * @param templateCode����ģ��id
	 * @return 
	 */
	public static boolean sendMessage(String phone,String verCode,String templateCode){
        //����ģ�������
        String json="{\"verCode\":\""+verCode+"\",\"time\":\""+time+"\"}";

        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);

        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //�����ش��������ڡ���Ϣ���ء��л�͸���ظò�����
        //�������û����Դ����Լ��¼��Ļ�ԱID������Ϣ����ʱ���û�ԱID��������ڣ��û����Ը��ݸû�ԱIDʶ������λ��Աʹ�������Ӧ��
        req.setExtend("");
        //�������ͣ�����ֵ����дnormal
        req.setSmsType("normal");
        //ǩ������
        req.setSmsFreeSignName("���ױ�");
        //����ģ����������ι���{"key":"value"}��key�������������ģ���еı�����һ�£��������֮���Զ��Ÿ�����
        req.setSmsParamString(json);
        //���Ž��պ���
        req.setRecNum(phone);
        //����ģ��ID
        req.setSmsTemplateCode(templateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean tf = rsp.isSuccess();
        return tf;
	}
	
	/**
	 * ����֪ͨ����(ѧԱȡ��Լ�̣������ܾ�Լ�̣�����ȷ��Լ������)
	 * @param phone
	 * @param name
	 * @param templateCode ����ģ��id
	 * @return boolean
	 */
	public static boolean sendNotifyMsg(String phone,String name,String templateCode){
        //����ģ�������
        String json="{\"name\":\""+name+"\"}";

        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);

        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //�����ش��������ڡ���Ϣ���ء��л�͸���ظò�����
        //�������û����Դ����Լ��¼��Ļ�ԱID������Ϣ����ʱ���û�ԱID��������ڣ��û����Ը��ݸû�ԱIDʶ������λ��Աʹ�������Ӧ��
        req.setExtend("");
        //�������ͣ�����ֵ����дnormal
        req.setSmsType("normal");
        //ǩ������
        req.setSmsFreeSignName("���ױ�");
        //����ģ����������ι���{"key":"value"}��key�������������ģ���еı�����һ�£��������֮���Զ��Ÿ�����
        req.setSmsParamString(json);
        //���Ž��պ���
        req.setRecNum(phone);
        //����ģ��ID
        req.setSmsTemplateCode(templateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean tf = rsp.isSuccess();
        return tf;
	}
	
	/**
	 * ����һ��Ų������(֪ͨ����Ų��)
	 * @param username Ų������������
	 * @param templateCode ����ģ��id
	 * @return boolean
	 */
	public static boolean sendMoveCarMsg(String phone,String username,String templateCode){
        //����ģ�������
        String json="{\"username\":\""+username+"\"}";

        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);

        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //�����ش��������ڡ���Ϣ���ء��л�͸���ظò�����
        //�������û����Դ����Լ��¼��Ļ�ԱID������Ϣ����ʱ���û�ԱID��������ڣ��û����Ը��ݸû�ԱIDʶ������λ��Աʹ�������Ӧ��
        req.setExtend("");
        //�������ͣ�����ֵ����дnormal
        req.setSmsType("normal");
        //ǩ������
        req.setSmsFreeSignName("���ױ�");
        //����ģ����������ι���{"key":"value"}��key�������������ģ���еı�����һ�£��������֮���Զ��Ÿ�����
        req.setSmsParamString(json);
        //���Ž��պ���
        req.setRecNum(phone);
        //����ģ��ID
        req.setSmsTemplateCode(templateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean tf = rsp.isSuccess();
        return tf;
	}
}
