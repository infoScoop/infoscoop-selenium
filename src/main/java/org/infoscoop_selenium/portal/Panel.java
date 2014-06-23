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
     * パネルのカラム数を返す
     * @param columnsId
     * @return
     */
    public int getNumberOfColumn(String columnsId) {
        return getColumns(columnsId).size();
    }

    /**
     * パネルの各カラムに配置されているガジェットのＩＤを返す
     * @param columnsId
     * @return
     */
    public List<List<String>> getDeployedGadgetIds(String columnsId) {
        List<List<String>> deployedGadgetIds = new ArrayList<>();

        List<WebElement> columns = getColumns(columnsId);
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

    private List<WebElement> getColumns(String columnsId) {
        return this.panelEl.findElements(By.cssSelector("#" + columnsId + " .column"));
    }

	
	/**
	 * ガジェット要素リストを返す
	 */
	public List<WebElement> getGadgetElements() {
		return this.panelEl.findElements(By.cssSelector(".widget"));
	}
}
