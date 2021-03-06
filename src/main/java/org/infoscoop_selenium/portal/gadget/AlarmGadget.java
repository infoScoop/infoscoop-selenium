package org.infoscoop_selenium.portal.gadget;

import java.util.Arrays;
import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/* TODO
 * カレンダー設定が固定になっているので、可変でも設定できるようにしておくと良い
 */

public class AlarmGadget extends Gadget{
	public AlarmGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}
	
	public static enum NOTIFY {
		ALERT("alert"),
		SHAKE("shake"),
		NONE("nothing");

		private final String notify;
		private NOTIFY(String notify){
			this.notify = notify;
		}
		public String getValue(){
			return notify;
		}
	}
	
	/**
	 * コンテンツエレメントを返す。
	 */
	public WebElement getContentElement() {
		WebElement gadget = super.getGadgetElement();
		WebElement content = gadget.findElement(By.cssSelector(".widgetShade .widgetBox .widgetContent"));
		return content;
	}
	
	/**
	 * 日付を返す。
	 */
	public String getDate() {
		super.focus();
		WebElement content = driver.findElement(By.xpath("//div[@id='small']"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * タイトルを返す。
	 * Gadget#getTitle と競合しないように AlarmGadget#getTitleStr とする
	 */
	public String getTitleStr() {
		super.focus();
		WebElement content = driver.findElement(By.xpath("//div[@id='title']"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * タイトル（エラー時メッセージ）を返す
	 */
	public String getTitleMessage() {
		super.focus();
		WebElement content = driver.findElement(By.xpath("//div[@id='title']/div[@class='message']"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * タイトル（エラー時エラー）を返す
	 */
	public String getTitleError() {
		super.focus();
		WebElement content = driver.findElement(By.xpath("//div[@id='title']/div[@class='error']"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * 時間を返す。
	 */
	public String getTime() {
		super.focus();
		WebElement content = driver.findElement(By.xpath("//div[@id='time']"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * アラーム設定
	 * @param title
	 * @param notify
	 * @param hour
	 * @param minute
	 */
	public void setAlarm(String title, NOTIFY notify, int dir, int hour, int minute){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		// タイトル
		setTitle(title);

		// お知らせ方法
		selectNotification(notify);

		// 年月日
		setCalendar(dir);

		// 時
		setHour(hour);

		// 分
		setMinute(minute);		
	}	
	
	/**
	 * タイトルエレメントを返す
	 */
	public WebElement getTitleElement() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return null;
		
		return driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_title']/input"));
	}
	
	/**
	 * タイトル設定
	 * @param title
	 */
	public void setTitle(String title){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		getTitleElement().sendKeys(title);
	}
	
	/**
	 * お知らせ方法エレメントを返す
	 */
	public WebElement getNotificationElement() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return null;
		
		return driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_method']/select"));
	}
	
	/**
	 * お知らせ方法変更
	 * @param notify
	 */
	public void selectNotification(NOTIFY notify){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		Select select = new Select(getNotificationElement());
		select.selectByValue(notify.getValue());
	}
	
	/**
	 * 年月日エレメントを返す
	 */
	public WebElement getCalendarElement() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return null;
		
		return driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_ymd']/div[@class='DatatypeCalendar']/input"));
	}
	
	/**
	 * 年月日設定
	 */
	public void setCalendar(int dir){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		// カレンダーを開く
		driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_ymd']/div[@class='DatatypeCalendar']/a")).click();		
		TestHelper.waitPresent(driver, By.className("CalendarComponent_Panel"));
		
		if (dir < 0) {
			// 前月にする
			driver.findElement(By.xpath("//table[@class='CalendarComponent_Table Calendar widgetContent']/thead//a[@class='calnavleft']")).click();
		} else if (dir > 0) {
			// 翌月にする
			driver.findElement(By.xpath("//table[@class='CalendarComponent_Table Calendar widgetContent']/thead//a[@class='calnavright']")).click();
		}
		// 日付を指定する
		driver.findElement(By.xpath("//table[@class='CalendarComponent_Table Calendar widgetContent']/tbody/tr[2]/td[2]/div")).click();
	}
	
	/**
	 * 時刻エレメントを返す
	 */
	public WebElement getHourElement() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return null;
		
		return driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_hour']/input"));
	}
	
	/**
	 * 時刻設定
	 * @param hour
	 */
	public void setHour(int hour){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		getHourElement().sendKeys(Integer.toString(hour));
	}
	
	/**
	 * 分エレメントを返す
	 */
	public WebElement getMinuteElement() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return null;
		
		return driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_minute']/input"));
	}
	
	/**
	 * 分設定
	 * @param minute
	 */
	public void setMinute(int minute){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_minute']/input")).sendKeys(Integer.toString(minute));
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
