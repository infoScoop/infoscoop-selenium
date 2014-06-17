package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class TopMenu {
	WebDriver driver;
	
	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * 指定IDのトップメニュー開く
	 */
	public void openTopMenu(String menuId){
		TestHelper.waitPresent(driver, By.id(menuId));
		
		// トップメニューにマウスオーバー
		Actions action = new Actions(driver);
//		action.moveToElement(driver.findElement(By.id(menuId))).click().build().perform();
		action.moveToElement(driver.findElement(By.id(menuId))).build().perform();
		TestHelper.sleep(1000);
		
		TestHelper.waitPresent(driver, By.xpath("//li[@id='" + menuId + "']//ul[1]"));
	}

	/**
	 * 指定IDのガジェットをドロップする
	 * e.g) asahi.com = news_asahi
	 */
	public Gadget dropGadget(String parentId, String id, int columnNum){
		return dropGadget(parentId, id, columnNum, GADGET_TYPE.GENERIC, true);
	}

	/**
	 * 指定IDのガジェットをドロップする
	 * e.g) asahi.com = news_asahi
	 */
	public Gadget dropGadget(String parentId, String id, int columnNum, GADGET_TYPE gadgetType){
		return dropGadget(parentId, id, columnNum, gadgetType, true);
	}
	
	/**
	 * 指定IDのガジェットをドロップする
	 * e.g) asahi.com = news_asahi
	 */
	public Gadget dropGadget(String parentId, String id, int columnNum, GADGET_TYPE gadgetType, boolean dropFlg){
		openTopMenu(parentId);
		
		WebElement dropElement = driver.findElement(By.xpath("//div[@class='column' and @colnum='" + columnNum + "']"));
		Point dropPoint = dropElement.getLocation();
		
		WebElement targetElement = driver.findElement(By.id("mi_" + id));
		
		Actions actions = new Actions(driver);

		// Firefox (on windows) だと固まる
//		actions.dragAndDropBy(targetElement, dropPoint.x, dropPoint.y).perform();
		
		// IE, FFで動作するコード
		actions.moveToElement(targetElement);
		actions.clickAndHold();
		actions.moveByOffset(dropPoint.x, dropPoint.y);
		if(dropFlg)
			actions.release();
		actions.build().perform();
		
		if(dropFlg){
			// FIXME: IEだとオーバーレイが残ってしまい、操作不能になる。できればWebDriverでなんとかしたい
			((JavascriptExecutor)driver).executeScript("IS_Portal.hideDragOverlay();");
			
			// メニューが残っている場合消去する
//			if(driver.findElement(By.xpath("//li[@id='etcWidgets']//img[@class='closeMenu']")).isDisplayed()){
//				try{
//					Thread.sleep(500);
//				}catch(Exception e){
//					throw new RuntimeException(e);
//				}
//				WebElement closeDiv = driver.findElement(By.xpath("//li[@id='etcWidgets']//img[@class='closeMenu']"));
//				actions.moveToElement(closeDiv);
//				actions.build().perform();
//			}
			
			String widgetId = dropElement.findElements(By.className("widget")).get(0).getAttribute("id");
			
//			return new Gadget(driver, widgetId);
			Class<Gadget> c = gadgetType.getValue();
			try {
				return (Gadget)c.getConstructor(new Class[]{WebDriver.class, String.class})
						.newInstance(new Object[]{driver, widgetId});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}
		}else{
			return null;
		}
	}

	/**
	 * まとめてドロップ
	 */
	public Gadget dropFolder(String parentId, int columnNum){
		return dropFolder(parentId, columnNum, GADGET_TYPE.GENERIC, true);
	}

	/**
	 * まとめてドロップ
	 */
	public Gadget dropFolder(String parentId, int columnNum, GADGET_TYPE gadgetType){
		return dropFolder(parentId, columnNum, gadgetType, true);
	}
	
	/**
	 * まとめてドロップ
	 */
	public Gadget dropFolder(String parentId, int columnNum, GADGET_TYPE gadgetType, boolean dropFlg){
		openTopMenu(parentId);
		
		WebElement dropElement = driver.findElement(By.xpath("//div[@class='column' and @colnum='" + columnNum + "']"));
		Point dropPoint = dropElement.getLocation();
		
		WebElement parentElement = driver.findElement(By.id("mg_" + parentId + "_0"));
		WebElement targetElement = parentElement.findElement(By.className("menufolderfeedTitle"));
		
		Actions actions = new Actions(driver);

		// Firefox (on windows) だと固まる
//		actions.dragAndDropBy(targetElement, dropPoint.x, dropPoint.y).perform();
		
		// IE, FFで動作するコード
		actions.moveToElement(targetElement);
		actions.clickAndHold();
		actions.moveByOffset(dropPoint.x, dropPoint.y);
		if(dropFlg)
			actions.release();
		actions.build().perform();
		
		if(dropFlg){
			// FIXME: IEだとオーバーレイが残ってしまい、操作不能になる。できればWebDriverでなんとかしたい
			((JavascriptExecutor)driver).executeScript("IS_Portal.hideDragOverlay();");
			
			// メニューが残っている場合消去する
//			if(driver.findElement(By.xpath("//li[@id='etcWidgets']//img[@class='closeMenu']")).isDisplayed()){
//				try{
//					Thread.sleep(500);
//				}catch(Exception e){
//					throw new RuntimeException(e);
//				}
//				WebElement closeDiv = driver.findElement(By.xpath("//li[@id='etcWidgets']//img[@class='closeMenu']"));
//				actions.moveToElement(closeDiv);
//				actions.build().perform();
//			}
			
			String widgetId = dropElement.findElements(By.className("widget")).get(0).getAttribute("id");
			
//			return new Gadget(driver, widgetId);
			Class<Gadget> c = gadgetType.getValue();
			try {
				return (Gadget)c.getConstructor(new Class[]{WebDriver.class, String.class})
						.newInstance(new Object[]{driver, widgetId});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}
		}else{
			return null;
		}
	}
}
