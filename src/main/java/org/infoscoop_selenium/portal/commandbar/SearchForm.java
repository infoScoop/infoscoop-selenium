package org.infoscoop_selenium.portal.commandbar;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchForm {
	WebDriver driver;
	CommandBar commandBar;
	
	public SearchForm(CommandBar commandBar, WebDriver driver) {
		this.driver = driver;
		this.commandBar = commandBar;
	}

	/**
	 * 検索実行
	 * @param word
	 */
	public void doSearch(String word){
		this.driver.findElement(By.id("searchTextInput")).sendKeys(word);
		this.driver.findElement(By.id("searchForm")).submit();
		
		WebElement searchIframe = this.driver.findElement(By.id("search-iframe"));
		TestHelper.waitPresent(searchIframe);
	}
}
