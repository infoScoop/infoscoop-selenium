package org.infoscoop_selenium;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.infoscoop_selenium.portal.Tab;
import org.infoscoop_selenium.portal.TopMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Portal {
	WebDriver driver;
	String url;
	CommandBar commandBar;
	Tab tab;
	TopMenu topMenu;
	
	public Portal(WebDriver driver, String url) {
		this.driver = driver;
		this.url = url;
		this.commandBar = new CommandBar(driver);
		this.tab = new Tab(driver);
		this.topMenu = new TopMenu(driver);
	}
	/*
	public void open(){
		this.driver.get("about:blank");
		logout();
		this.driver.get(url);
	}
	*/
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
		TestHelper.waitPresent(this.driver, By.id("columns0"));
	}
	public void logout(){
		this.driver.get(url + "/logout");
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
	
	public String getUrl(){
		return url;
	}
	
	public void close(){
		this.driver.close();
	}
}