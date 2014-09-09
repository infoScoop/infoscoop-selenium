package org.infoscoop_selenium.portal.gadget;

import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MessageGadget extends Gadget {    
	public MessageGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}

	/**
	 * ガジェットコンテンツの取得
	 */
	public WebElement getContentsElement() {
		return driver.findElement(By.xpath("//div[@class='widgetContent Message']/table/tbody/tr/td"));
	}
	
	/**
	 * ガジェットコンテンツの取得（最大化時/リスト）
	 */
	public WebElement getMaximaizeContentsListElement() {
		return driver.findElement(By.xpath("//div[@class='widgetContent Message maximize']/table/tbody/tr/td[@class='maximizeMsgListTd']"));
	}

	/**
	 * ガジェットコンテンツの取得（最大化時/メニュー）
	 */
	public WebElement getMaximaizeContentsMenuElement() {
		return driver.findElement(By.xpath("//div[@class='widgetContent Message maximize']/table/tbody/tr/td[@class='maximizeMsgMenuTd']/div[@class='maximizeMsgMenu']"));
	}
	
	/**
	 * グループ編集画面を開く
	 */
	public void openGroupEdit() {
		// ガジェットメニューを開く
		openMenu();
		driver.findElement(By.xpath("//div[@id='hm_w_etcWidgets_Message_editGroup']/a")).click();
	}
	
	/**
	 * 新しいグループを追加する
	 * @param name　グループ名
	 */
	public void addGroup(String name) {
	    openGroupEdit();

	    if (name != null) {
	        driver.findElement(By.cssSelector("#group-setting--tabs > li.add-group")).click();
	        changeTabName(driver.findElements(By.cssSelector("#group-setting--tabs > li.tab")).size() - 1, name);
	    } else {
	        // click "cancel" button
	        driver.findElement(By.cssSelector(".user-search-modal .footer input")).click();
	    }
	}
	
	/**
	 * グループ名を変更する
	 * @param no
	 * @param name
	 */
	public void changeTabName(int no, String name) {
	    WebElement userSearchModal = driver.findElement(By.className("user-search-modal"));
	    if (!userSearchModal.isDisplayed()) {
	        openGroupEdit();
	    }
	    
	    WebElement tab = driver.findElements(By.cssSelector("#group-setting--tabs > li.tab")).get(no);
	    tab.click();
	    tab.findElement(By.className("edit")).click();
	    tab.findElement(By.tagName("input")).sendKeys(Keys.chord(Keys.CONTROL, "a"), name);
	    
	    // click "cancel" button
	    driver.findElement(By.cssSelector(".user-search-modal .footer input")).click();
	}
	
	/**
	 * 公開メッセージ投稿
	 */
	public void sendPublicMessage(String msg, WebElement el) {
		// 公開メッセージを投稿するリンクをクリック
		el.findElement(By.className("messageSelf")).click();
		TestHelper.waitPresent(driver, By.xpath("//div[1]/textarea"));

		// メッセージの投稿
		el.findElement(By.xpath("//div[1]/textarea")).sendKeys(msg);
		el.findElement(By.xpath("//div[1]/div[3]/input[1]")).click();
		TestHelper.waitPresent(driver, By.className("latestMsg"));
	}
	
	/**
	 * メッセージ送信フォームのラベルの文字列を返却する
	 * @return
	 */
	public String getMessageTo() {
	    return driver.findElement(By.cssSelector("#" + super.getId() + " .messageTo")).getText();
	}
	
	/**
	 * 送信メッセージを入力するテキストエリアを返却する
	 * @return
	 */
    public WebElement getTextAreaElement() {
        try {
            return driver.findElement(By.cssSelector("#" + super.getId() + " textarea"));
        } catch (NoSuchElementException e) {
            throw e;
        }
    }
    
    /**
     * グループの帯（<div class="mesageGroup">）である要素を返却する
     * @param order
     * @return
     */
    public WebElement getMessageGroupElement(int order) {
        WebElement contents = getContentsElement();
        List<WebElement> msgGroupElms = contents.findElements(By.className("messageGroup"));
        if (msgGroupElms.size() >= order) {
            return msgGroupElms.get(order);
        } else {
            return null;
        }
    }

    /**
     * グループのメニュー項目（「受信メッセージ」、「送信メッセージ」など）部分である要素を返却する
     * @param order
     * @return
     */
    public WebElement getMessageUsersElement(int order) {
        WebElement contents = getContentsElement();
        List<WebElement> msgUsersElms = contents.findElements(By.className("messageUsers"));
        if (msgUsersElms.size() >= order) {
            return msgUsersElms.get(order);
        } else {
            return null;
        }
    }
    
    /**
     * 「システム」グループのRSSアイコンの要素を返却する
     * @param order
     * @return
     */
    public WebElement getSystemRssIcon(int order) {
        WebElement sysGrpUsers = getMessageUsersElement(0);
        List<WebElement> menuItems = sysGrpUsers.findElements(By.tagName("li"));
        if (menuItems.size() > order) {
            return menuItems.get(order).findElement(By.tagName("img"));
        } else {
            return null;
        }
    }
    
    /**
     * 「システム」グループの「お知らせ」の編集アイコンの要素を返却する
     * @return
     */
    public WebElement getSystemEditIcon() {
        WebElement sysGrpUsers = getMessageUsersElement(0);
        List<WebElement> menuItems = sysGrpUsers.findElements(By.tagName("li"));
        return menuItems.get(2).findElements(By.tagName("img")).get(1);
    }

	@Override
	public List<String> getSupportedHeaderIcons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSupportedMenuItems() {
		// TODO Auto-generated method stub
		return null;
	}
}
