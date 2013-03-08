package org.infoscoop_selenium.base;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.infoscoop_selenium.Portal;
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
	
	protected static final String TEST_USER_1 = "test_user1";
	protected static final String TEST_USER_2 = "test_user2";
	protected static final String TEST_USER_3 = "test_user3";
	protected static final String TEST_PASSWORD = "password";
	
	public IS_BaseItTestCase() {
		File file = new File("drivers/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		
		if(driver == null){
			// TODO: 実行時の引数でドライバを変える
			try {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setPlatform(Platform.WINDOWS);
				capabilities.setBrowserName("iexplorer");
				capabilities.setVersion("9");
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new RemoteWebDriver(new URL("http://172.22.113.136:4444/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
//			driver = new FirefoxDriver();
//			driver = new InternetExplorerDriver();
		}
		String url = "http://s00215:8080/infoscoop";
		portal = new Portal(driver, url);
	}
	
	@BeforeClass
	public static void doBeforeClass() {
		System.out.println("");
	}

	@Before
	public abstract void doBefore();

	@After
	public abstract void doAfter();

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
