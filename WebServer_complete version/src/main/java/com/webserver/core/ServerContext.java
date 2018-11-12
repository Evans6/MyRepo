package com.webserver.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ����������Ϣ����
 * @author ta
 *
 */
public class ServerContext {
	/**
	 * Servletӳ��
	 * key:��Ӧ��uri·��
	 * value:����������Servlet������
	 */
	private static Map<String,String> servletMapping = new HashMap<String,String>();
	
	static {
		initServletMapping();
	}
	/**
	 * ��ʼ��Servletӳ��
	 */
	private static void initServletMapping() {
//		servletMapping.put("/myweb/reg", "com.webserver.servlets.RegServlet");
//		servletMapping.put("/myweb/login", "com.webserver.servlets.LoginServlet");
//		servletMapping.put("/myweb/showAllUser", "com.webserver.servlets.ShowAllUserServlet");
		/*
		 * ����conf/servlets.xml�ļ�
		 * ����Ԫ���е�����<servlet>Ԫ�ػ�ȡ����������
		 * ��uri���Ե�ֵ��Ϊkey,��class���Ե�ֵ��Ϊvalue
		 * ���浽servletMapping����ɳ�ʼ����
		 */
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new File("conf/servlets.xml"));
			Element root = doc.getRootElement();
			List<Element> servletList = root.elements();
			for(Element servletEle : servletList) {
				String uri = servletEle.attributeValue("uri");
				String className = servletEle.attributeValue("class");
				servletMapping.put(uri, className);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * ���ݸ�����uri��ȡ��Ӧ��Servlet��������������uri��Ч
	 * �򷵻�ֵΪnull
	 * @param uri
	 * @return
	 */
	public static String getServletNameByUri(String uri) {
		return servletMapping.get(uri);
	}
	
	public static void main(String[] args) {
		String servletName = getServletNameByUri("/myweb/reg");
		System.out.println("servletName:"+servletName);
	}
}

















