package org.infoscoop_selenium;

import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.infoscoop_selenium.portal.Panel;
import org.infoscoop_selenium.portal.SideBar;
import org.infoscoop_selenium.portal.Tab;
import org.infoscoop_selenium.portal.TopMenu;
import org.infoscoop_selenium.properties.TestEnv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Portal {
	WebDriver driver;
	String url;
	CommandBar commandBar;
	Tab tab;
	TopMenu topMenu;
	SideBar sideBar;
    Panel panel;
	WebDriver messageConsoleDriver;
	AdminPage adminPage;
	
	public Portal(WebDriver driver, String url) {
		this.driver = driver;
		this.url = url;
		this.commandBar = new CommandBar(driver);
		this.tab = new Tab(driver);
		this.topMenu = new TopMenu(driver);
		this.sideBar = new SideBar(driver);
        this.panel = new Panel(driver);

		WindowManager.init(driver);
	}
	
	public void login(){
		login(TestEnv.getInstance().getUserId(), TestEnv.getInstance().getPasswd());
	}
	
	public void login(String uid, String pass){
		this.driver.get(url + "/login.jsp");
		this.driver.findElement(By.id("uid")).sendKeys(uid);
		this.driver.findElement(By.id("password")).sendKeys(pass);
		this.driver.findElement(By.id("loginform")).submit();

		// ロードの完了を待つ
		/*
		TestHelper.waitPresent(driver, By.id("divOverlay"));
		TestHelper.waitInvisible(driver, By.id("divOverlay"));
		*/
		waitPortalLoadComplete(driver);
	}
	public void logout(){
		this.driver.get(url + "/logout");
	}
	
	/**
	 * 管理画面を開き、Adminオブジェクトを返す
	 * @return
	 */
	public AdminPage openAdminPage(){
		getCommandBar().openMenu();
		WebDriver adminPageDriver = WindowManager.getInstance().newWindow(driver.findElement(By.id("admin-link")));
		this.adminPage = new AdminPage(adminPageDriver);
		return this.adminPage;
	}
	
	/**
	 * メッセージコンソールを開く
	 * @return
	 */
	public WebDriver openMessageConsole(){
        TestHelper.waitPresent(driver, By.id("messageIcon"));
		this.messageConsoleDriver = WindowManager.getInstance().newWindow(driver.findElement(By.id("messageIcon")));
		return this.messageConsoleDriver;
	}
	
	/**
	 * メッセージコンソールを閉じる
	 */
	public void closeMessageConsole(){
		if(this.messageConsoleDriver != null)
			 WindowManager.getInstance().closeWindow(this.messageConsoleDriver.getWindowHandle());
	}

	public CommandBar getCommandBar(){
		return commandBar;
	}
	
	public Tab getTab(){
		return tab;
	}
	
	public TopMenu getTopMenu(){
		return topMenu;
	}
	
	public SideBar getSideBar(){
		return sideBar;
	}

	public Panel getPanel() {
	    return panel;
	}

	public String getUrl(){
		return url;
	}
	
	public void close(){
		this.driver.close();
	}
	
	public static void waitPortalLoadComplete(WebDriver driver){
		sleep(1000);
		TestHelper.waitPresent(driver, By.id("columns0"), 20);
	}
	
	private static void sleep(long sleep){
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
