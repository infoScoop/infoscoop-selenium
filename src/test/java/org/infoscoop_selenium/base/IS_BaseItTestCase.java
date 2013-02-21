package org.infoscoop_selenium.base;
import java.io.File;

import org.infoscoop_selenium.Portal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public abstract class IS_BaseItTestCase {
	private static WebDriver driver;
	private static Portal portal;
	
	static{
		File file = new File("drivers/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		init();
	}
	
	private static void init(){
		if(driver == null){
			// TODO: 実行時の引数でドライバを変える
//			WebDriver driver = new RemoteWebDriver(new URL("http://shd092v:4444/wd/hub"), DesiredCapabilities.firefox());
//			driver = new FirefoxDriver();
			driver = new InternetExplorerDriver();
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
