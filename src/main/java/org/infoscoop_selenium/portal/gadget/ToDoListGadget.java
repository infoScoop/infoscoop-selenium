package org.infoscoop_selenium.portal.gadget;

import java.util.Arrays;
import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ToDoListGadget extends Gadget{
	public ToDoListGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}
	
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
	
	/**
	 * ToDoを追加
	 * @param msg
	 */
	public void addToDo(String msg){
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		driver.findElement(By.id(super.getId()+"_inputText")).sendKeys(msg);
		driver.findElement(By.xpath("//table[@class='todoAddTable']/tbody/tr/td[2]/input[@type='button']")).click();		
		TestHelper.backToTopFrame(driver);
	}

	/**
	 * 優先度の変更
	 * @param order (ToDoの上から数えての順番)
	 * @param priority
	 */
	public void changePriority(int order, PRIORITY priority){
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		driver.findElement(By.xpath("//tbody[@id='"+super.getId()+"_list']/tr["+order+"]/td[@class='todoPriorityTd']/div")).click();
		Select select = new Select(driver.findElement(By.xpath("//tbody[@id='"+super.getId()+"_list']/tr["+order+"]/td[@class='todoPriorityTd']/select")));
		select.selectByIndex(priority.getValue());
		TestHelper.backToTopFrame(driver);		
	}
	
	/**
	 * チェックする
	 * @param order (ToDoの上から数えての順番)
	 */
	public void checkToDo(int order){
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		driver.findElement(By.xpath("//tbody[@id='"+super.getId()+"_list']/tr["+order+"]/td[@class='todoCheckTd']/input")).click();
		TestHelper.backToTopFrame(driver);		
	}
	
	/**
	 * フォントサイズの変更
	 * @param fontSize
	 */
	public void changeFontSize(FONTSIZE fontSize) {
		getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		Select font = new Select(driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_fontSize']/select")));
		font.selectByValue(fontSize.getValue());
		
		// ガジェット設定を閉じる
		getGadgetPreference().ok();
	}
	
	/**
	 * TODO追加テキストフォームを返す
	 * @return
	 */
	public WebElement getTodoAddTextBox(){
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		WebElement textBox = driver.findElement(By.cssSelector(".todoAddTextBox"));
		TestHelper.backToTopFrame(driver);
		
		return textBox;
	}

	/**
	 * TODO追加ボタンを返す
	 * @return
	 */
	public WebElement getTodoAddButton(){
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		WebElement addButton =  driver.findElement(By.cssSelector(".todoAddTable input[type=\"button\"]"));
		TestHelper.backToTopFrame(driver);
		
		return addButton;
	}

	/**
	 * TODO登録数を返す
	 * @return
	 */
	public int getTodoLength(){
		int len;
		
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		len = driver.findElements(By.cssSelector(".todoListTable tr")).size();
		TestHelper.backToTopFrame(driver);
		
		return len;
	}

	@Override
	public List<String> getSupportedHeaderIcons() {
		return Arrays.asList(Gadget.ICON_TYPE_MINIMIZE, Gadget.ICON_TYPE_SHOWTOOLS);
	}

	@Override
	public List<String> getSupportedMenuItems() {
		return Arrays.asList(Gadget.MENU_TYPE_EDIT, Gadget.MENU_TYPE_DELETE);
	}
	
}
