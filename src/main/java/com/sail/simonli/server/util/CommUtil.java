package com.sail.simonli.server.util;


import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class CommUtil {

	public static String getRandNum(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	public static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	public static String getMD5(byte[] data) {
		String str = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);
			byte[] b = md.digest();

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				int i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return str;
	}

	public static void parse(String protocolXML) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(
					protocolXML)));

			Element root = doc.getDocumentElement();
			NodeList books = root.getChildNodes();
			if (books != null) {
				for (int i = 0; i < books.getLength(); i++) {
					Node book = books.item(i);
					System.out.println("节点=" + book.getNodeName() + "\ttext="
							+ book.getFirstChild().getNodeValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断登录时间
	 * 
	 * @param LastLoginTime
	 * @param logincount
	 * @return
	 */
	public static boolean EqTime(String LastLoginTime, int logincount) {
		if ((LastLoginTime == null) || (LastLoginTime.equals(""))) {
			return true;
		}
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String newtimeStr = df.format(new Date());
			Date now = df.parse(newtimeStr);
			Date date = df.parse(LastLoginTime);
			System.out.println(now.getTime());
			System.out.println(date.getTime());
			long l = now.getTime() - date.getTime();
			long min = l / 60000L;
			if ((logincount >= 3) && (min < 7L)) {
				return false;
			}
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	
	 public static <T> T getBean(Class<T> clazz, HttpServletRequest request) {
	        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
	        return factory.getBean(clazz);
	    }

 

}
