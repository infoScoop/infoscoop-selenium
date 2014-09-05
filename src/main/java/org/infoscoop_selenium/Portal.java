package org.infoscoop_selenium;

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
	WebDriver messageConsoleDriver;
	AdminPage adminPage;
	
	public Portal(WebDriver driver, String url) {
		this.driver = driver;
		this.url = url;
		this.commandBar = new CommandBar(driver);
		this.tab = new Tab(driver);
		this.topMenu = new TopMenu(driver);
		this.sideBar = new SideBar(driver);

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
	
	public void toLoginPage(){
		this.driver.navigate().to(url + "/login.jsp");
	}
	
	/**
	 * 管理画面を開き、Adminオブジェクトを返す
	 * @return
	 */
	public AdminPage openAdminPage(){
		getCommandBar().openMenu();
		WebDriver adminPageDriver = WindowManager.getInstance().newWindow(driver.findElement(By.id("admin-link")));
		this.adminPage = new AdminPage(adminPageDriver);
		
		TestHelper.waitPresent(adminPageDriver, By.id("admin-body"));
		
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

	public Panel getPanel(String tabId) {
	    return new Panel(driver, tabId);
	}

	public String getUrl(){
		return url;
	}
	
	public boolean isPanelVisible(){
		return this.driver.findElement(By.id("panels")).isDisplayed();
	}
	
	public void close(){
		this.driver.close();
	}
	
	public static void waitPortalLoadComplete(WebDriver driver){
		sleep(1000);
		TestHelper.waitPresent(driver, By.id("columns0"), 20);
	}
	
	/**
	 * パネル要素の取得
	 * @return
	 */
	public WebElement getPanels(){
		return this.driver.findElement(By.id("panels"));
	}
	
	private static void sleep(long sleep){
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
