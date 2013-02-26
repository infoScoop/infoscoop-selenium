package org.infoscoop_selenium.portal.gadget;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ToDoListGadget {
	WebDriver driver;
	Gadget gadget;
	
	public static enum PRIORITY {
		HIGH(0),
		MIDDLE(1),
		LOW(2);

		private final int priority;
		private PRIORITY(int priority){
			this.priority = priority;
		}
		public int getValue(){
			return priority;
		}
	}
	
	public static enum FONTSIZE {
		LARGE("large"),
		NORMAL("normal");
		private final String fontSize;
		private FONTSIZE(String fontSize){
			this.fontSize = fontSize;
		}
		public String getValue(){
			return fontSize;
		}
	}
	
	public ToDoListGadget(Gadget gadget, WebDriver driver){
		this.driver = driver;
		this.gadget = gadget;
	}
	
	/**
	 * ToDoを追加
	 * @param widgetId
	 * @param msg
	 */
	public void addToDo(String widgetId, String msg){
		TestHelper.switchToFrame(driver, "ifrm_"+widgetId);
		driver.findElement(By.id(widgetId+"_inputText")).sendKeys(msg);
		driver.findElement(By.xpath("//table[@class='todoAddTable']/tbody/tr/td[2]/input[@type='button']")).click();		
		TestHelper.backToTopFrame(driver);
	}

	/**
	 * 優先度の変更
	 * @param widgetId
	 * @param order (ToDoの上から数えての順番)
	 * @param priority
	 */
	public void changePriority(String widgetId, int order, PRIORITY priority){
		TestHelper.switchToFrame(driver, "ifrm_"+widgetId);
		driver.findElement(By.xpath("//tbody[@id='"+widgetId+"_list']/tr["+order+"]/td[@class='todoPriorityTd']/div")).click();
		Select select = new Select(driver.findElement(By.xpath("//tbody[@id='"+widgetId+"_list']/tr["+order+"]/td[@class='todoPriorityTd']/select")));
		select.selectByIndex(priority.getValue());
		TestHelper.backToTopFrame(driver);		
	}
	
	/**
	 * チェックする
	 * @param widgetId
	 * @param order (ToDoの上から数えての順番)
	 */
	public void checkToDo(String widgetId, int order){
		TestHelper.switchToFrame(driver, "ifrm_"+widgetId);
		driver.findElement(By.xpath("//tbody[@id='"+widgetId+"_list']/tr["+order+"]/td[@class='todoCheckTd']/input")).click();
		TestHelper.backToTopFrame(driver);		
	}
	
	/**
	 * フォントサイズの変更
	 * @param widgetId
	 * @param fontSize
	 */
	public void changeFontSize(String widgetId, FONTSIZE fontSize) {
		gadget.getGadgetPreference().show(widgetId);

		if(!driver.findElement(By.id("frm_"+widgetId)).isDisplayed())
			return;
		
		Select font = new Select(driver.findElement(By.xpath("//td[@id='eb_"+widgetId+"_fontSize']/select")));
		font.selectByValue(fontSize.getValue());
		
		// ガジェット設定を閉じる
		gadget.getGadgetPreference().ok(widgetId);
	}
}
