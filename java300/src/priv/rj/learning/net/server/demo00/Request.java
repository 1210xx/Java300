package priv.rj.learning.net.server.demo00;

import priv.rj.learning.net.server.util.CloseUtil;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * ��װrequest
 * @author Administrator
 *
 */
public class Request {
	//����ʽ
	private String method;
	//������Դ
	private String url;
	//�������
	private Map<String,List<String>> parameterMapValues;
	
	//�ڲ�
	public static final String CRLF="\r\n";
	private InputStream is;
	private String requestInfo;
	
	public Request(){
		method ="";
		url ="";
		parameterMapValues=new HashMap<String,List<String>>();
		requestInfo="";
	}
	public Request(InputStream is){
		this();
		this.is=is;
		try {
			byte[] data = new byte[20480];
			int len = is.read(data);
			requestInfo = new String(data, 0, len);
		} catch (Exception e) {
			return ;
		}
		//����������Ϣ
		parseRequestInfo();
	}
	/**
	 * ����������Ϣ
	 */
	private void parseRequestInfo(){
		if(null==requestInfo ||(requestInfo=requestInfo.trim()).equals("")){
			return ;
		}		
		/**
		 * =====================================
		 * ����Ϣ�����зֽ�� :����ʽ    ����·��   �������(get���ܴ���)
		 *   ��:GET /index.html?name=123&pwd=5456 HTTP/1.1
		 * 
		 * ���Ϊpost��ʽ��������������� ���������
		 * 
		 * ˼·:
		 * 1)����ʽ :�ҳ���һ��/  ��ȡ����
		 * 2)������Դ:�ҳ���һ��/   HTTP/ 
		 * =====================================
		 */
		String paramString =""; //����������� 
		
		//1����ȡ����ʽ
		String firstLine =requestInfo.substring(0,requestInfo.indexOf(CRLF));
		int idx =requestInfo.indexOf("/"); // /��λ��
		this.method=firstLine.substring(0, idx).trim();
		String urlStr =firstLine.substring(idx,firstLine.indexOf("HTTP/")).trim();
		if(this.method.equalsIgnoreCase("post")){
			this.url=urlStr;		
			paramString=requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
			
		}else if(this.method.equalsIgnoreCase("get")){
			if(urlStr.contains("?")){ //�Ƿ���ڲ���
				String[] urlArray=urlStr.split("\\?");
				this.url=urlArray[0];
				paramString=urlArray[1];//����������� 
			}else{
				this.url=urlStr;			
			}
		}
		
	
		//�������������
		if(paramString.equals("")){
			return ;
		}
		//2�������������װ��Map��	
		parseParams(paramString);
	}
	private void parseParams(String paramString){
		//�ָ� ���ַ���ת������
		StringTokenizer token=new StringTokenizer(paramString,"&");
		while(token.hasMoreTokens()){
			String keyValue =token.nextToken();
			String[] keyValues=keyValue.split("=");
			if(keyValues.length==1){
				keyValues =Arrays.copyOf(keyValues, 2);
				keyValues[1] =null;
			}
			
			String key = keyValues[0].trim();
			String value = null==keyValues[1]?null:decode(keyValues[1].trim(),"gbk");
			//ת����Map �ּ�
			if(!parameterMapValues.containsKey(key)){
				parameterMapValues.put(key,new ArrayList<String>());
			}
			
			List<String> values =parameterMapValues.get(key);
			values.add(value);			
		}		
		
	}
	/**
	 * �������
	 * @param value
	 * @param code
	 * @return
	 */
	private String decode(String value,String code){
		try {
			return java.net.URLDecoder.decode(value, code);
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		return null;
	}
	/**
	 * ����ҳ���name ��ȡ��Ӧ�Ķ��ֵ
	 * @param args
	 */
	public String[] getParameterValues(String name){
		List<String> values=null;
		if((values=parameterMapValues.get(name))==null){
			return null;
		}else{
			return values.toArray(new String[0]);
		}
	}
	/**
	 * ����ҳ���name ��ȡ��Ӧ�ĵ���ֵ
	 * @param args
	 */
	public String getParameter(String name){
		String[] values =getParameterValues(name);
		if(null==values){
			return null;
		}
		return values[0];
	}
	public String getUrl() {
		return url;
	}
	
	public void close(){
		CloseUtil.closeIO(is);
	}
}
