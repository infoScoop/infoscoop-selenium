package org.infoscoop_selenium.base;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.infoscoop_selenium.Portal;
import org.infoscoop_selenium.properties.TestEnv;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public abstract class IS_BaseItTestCase {
	private static WebDriver driver;
	private static Portal portal;
	private static final String BROWSER_IE8 = "IE8";
	private static final String BROWSER_IE9 = "IE9";
	private static final String BROWSER_FF = "FireFox";
	
	public IS_BaseItTestCase() {
		File file = new File("drivers/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		
		TestEnv env = TestEnv.getInstance();
		boolean isLocal = env.getType().equals("local");
		
		if(isLocal){
			switch(env.getBrowser()){
				case BROWSER_IE8:
				case BROWSER_IE9:
					driver = new InternetExplorerDriver();
					break;
				case BROWSER_FF:
					driver = new FirefoxDriver();
			}
		} else {
			DesiredCapabilities capabilities = null;
			String version = null;
			try {
				switch(env.getBrowser()){
				case BROWSER_IE8:
					version = "8";
				case BROWSER_IE9:
					version = (version == null)? "9" : version;
					capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setPlatform(Platform.WINDOWS);
					capabilities.setBrowserName("iexplorer");
					capabilities.setVersion("9");
					capabilities.setCapability("maxInstances", 5);
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					break;
				case BROWSER_FF:
					capabilities = DesiredCapabilities.firefox();
					capabilities.setPlatform(Platform.WINDOWS);
					capabilities.setBrowserName("firefox");
				}
				
				driver = new RemoteWebDriver(new URL(env.getRemoteUrl()), capabilities);
			} catch (MalformedURLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		driver.manage().window().maximize();
		portal = new Portal(driver, env.getAppUrl());
	}
	
	@BeforeClass
	public static void doBeforeClass() {
	}

	@Before
	public abstract void doBefore();

	@After
	public void doAfter(){
//		driver.close();
		driver.quit();
	}

	@AfterClass
	public static void doAfterClass() {
		driver.quit();
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public Portal getPortal(){
		return portal;
	}
}
