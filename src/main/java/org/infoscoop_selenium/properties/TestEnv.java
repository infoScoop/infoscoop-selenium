package org.infoscoop_selenium.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class TestEnv {
	private static TestEnv instance = new TestEnv();
	private String userId;
	private String passwd;
	private String browser;
	private String type;
	private String remoteUrl;
	private String appUrl;
	
	private TestEnv() {
		Properties properties = new Properties();
		InputStream inStream = getClass().getClassLoader().getResourceAsStream("env.properties");
		try {
			properties.load(new InputStreamReader(inStream, "UTF-8"));
			this.appUrl = properties.getProperty("test.app.url").trim();
			this.userId = properties.getProperty("test.user.id").trim();
			this.passwd = properties.getProperty("test.user.passwd").trim();
			this.browser = properties.getProperty("test.browser").trim();
			this.type = properties.getProperty("test.type").trim();
			this.remoteUrl = properties.getProperty("test.remote_url").trim();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TestEnv getInstance(){
		return instance;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getBrowser() {
		return browser;
	}

	public String getType() {
		return type;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public static void main(String[] args) {
		System.out.println(TestEnv.getInstance().getUserId());
		System.out.println(TestEnv.getInstance().getPasswd());
		System.out.println(TestEnv.getInstance().getRemoteUrl());
		System.out.println(TestEnv.getInstance().getType());
		System.out.println(TestEnv.getInstance().getBrowser());
		System.out.println(TestEnv.getInstance().getAppUrl());
	}
}
