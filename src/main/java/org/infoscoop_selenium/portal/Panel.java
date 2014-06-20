package org.infoscoop_selenium.portal;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Panel {

    WebDriver driver;

    public Panel(WebDriver driver) {
        this.driver = driver;
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
        return driver.findElements(By.cssSelector("#" + columnsId + " .column"));
    }

}
