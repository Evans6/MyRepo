package com.webserver.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 服务端相关信息定义
 * @author ta
 *
 */
public class ServerContext {
	/**
	 * Servlet映射
	 * key:对应的uri路径
	 * value:处理该请求的Servlet的类名
	 */
	private static Map<String,String> servletMapping = new HashMap<String,String>();
	
	static {
		initServletMapping();
	}
	/**
	 * 初始化Servlet映射
	 */
	private static void initServletMapping() {
//		servletMapping.put("/myweb/reg", "com.webserver.servlets.RegServlet");
//		servletMapping.put("/myweb/login", "com.webserver.servlets.LoginServlet");
//		servletMapping.put("/myweb/showAllUser", "com.webserver.servlets.ShowAllUserServlet");
		/*
		 * 解析conf/servlets.xml文件
		 * 将根元素中的所有<servlet>元素获取出来，并将
		 * 其uri属性的值作为key,将class属性的值作为value
		 * 保存到servletMapping中完成初始化。
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
	 * 根据给定的uri获取对应的Servlet类名。若给定的uri无效
	 * 则返回值为null
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

















