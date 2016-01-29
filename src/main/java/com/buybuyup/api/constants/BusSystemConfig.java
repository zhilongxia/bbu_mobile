package com.buybuyup.api.constants;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BusSystemConfig {

	public static String[] notFiltrates = null;

	public static String[] notAjaxFiltrates = null;

	public static String[] apiNeedLoginFilter = null;

	public static int contentFileUpMethod;
	public static String contentFileUpLocalPath;
	public static String contentFileUpFtpIp;
	public static int contentFileUpFtpPort;
	public static String fileUploadPath;

	public static void init() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(
				new File(SystemConstantInit.CLASS_REAL_PATH.replaceAll("%20", " ") + "/" + "bus-system-config.xml"));
		Element root = document.getRootElement();

		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element el = (Element) i.next();

			if ("not-filtrates".equals(el.getName())) {
				List<String> ls = new ArrayList<String>();
				List<?> eus = el.elements();

				Element eu;
				for (Iterator<?> iterator = eus.iterator(); iterator.hasNext(); ls.add(eu.getText()))
					eu = (Element) iterator.next();

				notFiltrates = (String[]) ls.toArray(new String[0]);

				System.out.println(" set systemConfig notFiltrates value is :" + ls.toString());

				System.out.println(" ------------notFiltrates start-----------");

				for (String str : notFiltrates) {
					System.out.println("--" + str + "---");
				}
				System.out.println(" ------------notFiltrates  end -----------");
			}
			if ("notAjaxFiltrates".equals(el.getName())) {
				List<String> ls = new ArrayList<String>();
				List<?> eus = el.elements();

				Element eu;
				for (Iterator<?> iterator = eus.iterator(); iterator.hasNext(); ls.add(eu.getText()))
					eu = (Element) iterator.next();

				notAjaxFiltrates = (String[]) ls.toArray(new String[0]);

				System.out.println(" set systemConfig notAjaxFiltrates value is :" + ls.toString());

				System.out.println(" ------------notAjaxFiltrates start-----------");

				for (String str : notAjaxFiltrates) {
					System.out.println("--" + str + "---");
				}
				System.out.println(" ------------notAjaxFiltrates  end -----------");
			}
			if ("apiNeedLoginFilter".equals(el.getName())) {
				List<String> ls = new ArrayList<String>();
				List<?> eus = el.elements();

				Element eu;
				for (Iterator<?> iterator = eus.iterator(); iterator.hasNext(); ls.add(eu.getText()))
					eu = (Element) iterator.next();

				apiNeedLoginFilter = (String[]) ls.toArray(new String[0]);

				System.out.println(" set systemConfig apiNeedLoginFilter value is :" + ls.toString());

				System.out.println(" ------------apiNeedLoginFilter start-----------");

				for (String str : apiNeedLoginFilter) {
					System.out.println("--" + str + "---");
				}
				System.out.println(" ------------apiNeedLoginFilter  end -----------");
			}
			if ("local-method".equals(el.getName())) {
				for (Iterator<?> m = el.elementIterator(); m.hasNext();) {
					Element elm = (Element) m.next();

					if ("path".equals(elm.getName())) {
						contentFileUpLocalPath = elm.getTextTrim();
						System.out
								.println(" set systemConfig contentFileUpLocalPath value is " + contentFileUpLocalPath);
					}
				}
			}
		}
	}

	public static HashMap<String, String> weChat_config() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(
				new File(SystemConstantInit.CLASS_REAL_PATH.replaceAll("%20", " ") + "/" + "bus-system-config.xml"));
		Element root = document.getRootElement();
		HashMap<String, String> map = new HashMap<String, String>();
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element el = (Element) i.next();
			List<String> weChatList = get_weChat_keyList();
			if (weChatList.contains(el.getName())) {
				System.out.println(el.getName() + " value is " + el.getTextTrim());
				map.put(el.getName(), el.getTextTrim());
			}
		}
		return map;
	}

	public static HashMap<String, String> smsSend_config() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(
				new File(SystemConstantInit.CLASS_REAL_PATH.replaceAll("%20", " ") + "/" + "bus-system-config.xml"));
		Element root = document.getRootElement();
		HashMap<String, String> map = new HashMap<String, String>();
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element el = (Element) i.next();
			List<String> weChatList = get_smsSend_List();
			if (weChatList.contains(el.getName())) {
				System.out.println(el.getName() + " value is " + el.getTextTrim());
				map.put(el.getName(), el.getTextTrim());
			}
		}
		return map;
	}

	public static String host_config() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(
				new File(SystemConstantInit.CLASS_REAL_PATH.replaceAll("%20", " ") + "/" + "bus-system-config.xml"));
		Element root = document.getRootElement();
		String host_config = "";
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element el = (Element) i.next();

			if ("host_config".equals(el.getName())) {
				host_config = el.getTextTrim();
				System.out.println("host_config value is " + host_config);
				break;
			}
		}
		return host_config;
	}

	private static List<String> get_weChat_keyList() {

		List<String> weChatList = new ArrayList<String>();
		weChatList.add("weChatCallBack");
		weChatList.add("qrconnect");
		weChatList.add("weChatSecret");
		weChatList.add("weChatAppId");
		weChatList.add("weChatApiUrl");
		weChatList.add("wechatToken");
		return weChatList;
	}

	private static List<String> get_smsSend_List() {

		List<String> weChatList = new ArrayList<String>();
		weChatList.add("smsSendUrl");
		weChatList.add("smsUid");
		weChatList.add("smsPwd");
		weChatList.add("smsSignature");
		return weChatList;
	}

}
