package org.infoscoop_selenium.portal.gadget;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
	 * アラーム設定
	 * @param title
	 * @param notify
	 * @param hour
	 * @param minute
	 */
	public void setAlarm(String title, NOTIFY notify, int hour, int minute){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		// タイトル
		setTitle(title);

		// お知らせ方法
		selectNotification(notify);

		// 年月日
		setCalendar();

		// 時
		setHour(hour);

		// 分
		setMinute(minute);		
	}	
	
	/**
	 * タイトル設定
	 * @param title
	 */
	public void setTitle(String title){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_title']/input")).sendKeys(title);
	}
	
	/**
	 * お知らせ方法変更
	 * @param notify
	 */
	public void selectNotification(NOTIFY notify){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		Select select = new Select(driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_method']/select")));
		select.selectByValue(notify.getValue());
	}
	
	/**
	 * 年月日設定
	 */
	public void setCalendar(){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		// カレンダーを開く
		driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_ymd']/div[@class='DatatypeCalendar']/a")).click();		
		TestHelper.waitPresent(driver, By.className("CalendarComponent_Panel"));
		
		// 日付を指定する
		driver.findElement(By.xpath("//table[@class='CalendarComponent_Table calendar widgetContent']/tbody/tr[2]/td[2]/div")).click();
	}
	
	/**
	 * 時刻設定
	 * @param hour
	 */
	public void setHour(int hour){
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_hour']/input")).sendKeys(Integer.toString(hour));
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
}
