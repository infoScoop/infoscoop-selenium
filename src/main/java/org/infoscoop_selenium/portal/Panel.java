package org.infoscoop_selenium.portal;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Panel {

    WebDriver driver;
    String tabId;
    String panelId;
    WebElement panelEl;

    public Panel(WebDriver driver, String tabId) {
        this.driver = driver;
        this.tabId = tabId;
        this.panelId = tabId.replace("tab", "panel");
        this.panelEl = driver.findElement(By.id(this.panelId));
    }

    /**
     * パネルの各カラムに配置されているガジェットのＩＤを返す
     * @param columnsId
     * @return
     */
    public List<List<String>> getDeployedGadgetIds() {
        List<List<String>> deployedGadgetIds = new ArrayList<>();

        List<WebElement> columns =  this.panelEl.findElements(By.cssSelector(".column"));
        for (WebElement column : columns) {
            List<String> ids = new ArrayList<>();
            List<WebElement> gadgets = column.findElements(By.cssSelector(".widget"));
            for (WebElement gadget : gadgets) {
                ids.add(gadget.getAttribute("id"));
            }
            deployedGadgetIds.add(ids);
        }

        return deployedGadgetIds;
    }
    
	/**
	 * カラム要素リストを返す
	 */
	public List<WebElement> getColumnElements() {
		return this.panelEl.findElements(By.cssSelector(".column"));
	}

	/**
	 * ガジェット要素リストを返す
	 */
	public List<WebElement> getGadgetElements() {
		return this.panelEl.findElements(By.cssSelector(".widget"));
	}
	
	/**
	 * 列数を返す
	 */
	public int getColumnLength() {
		return this.panelEl.findElements(By.cssSelector(".column")).size();
	}

	/**
	 * 列幅調整バーを返す
	 */
	public WebElement getAdjustBar(int idx) {
		return this.panelEl.findElements(By.cssSelector(".adjustBarOut")).get(idx);
	}
}
