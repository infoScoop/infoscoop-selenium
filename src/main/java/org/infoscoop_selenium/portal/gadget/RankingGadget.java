package org.infoscoop_selenium.portal.gadget;

import java.util.Arrays;
import java.util.List;

import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RankingGadget extends Gadget {

    public RankingGadget(WebDriver driver, String gadgetId) {
        super(driver, gadgetId);
    }

    /**
     * ランキングの表示件数が0の場合はtrueを返却する
     * 
     * @return
     */
    public boolean isZeroCount() {
        // css selector: #w_etcWidgets_WidgetRanking .widgetContent
        WebElement widgetContent = driver.findElement(By.cssSelector("#" + super.getId() + " .widgetContent"));
        if ((widgetContent.findElement(By.cssSelector(".widgetRankNone"))) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * ランキング件数が0の時に表示されるメッセージを返却する
     * 
     * @return
     */
    public String getNoneMessage() {
        if (isZeroCount()) {
            WebElement widgetRankNone = driver.findElement(By.cssSelector(
                    "#" + super.getId() + " .widgetContent .widgetRankNone"));
            return widgetRankNone.getText();
        } else {
            return null;
        }
    }
    
    /**
     * 最終更新日時のメッセージを返却する
     * 
     * @return
     */
    public String getLastUpdateMessage() {
        WebElement widgetRankTime = driver.findElement(By.cssSelector(
                "#" + super.getId() + " .widgetContent .widgetRankTime"));
        return widgetRankTime.getText();
    }

    @Override
    public List<String> getSupportedHeaderIcons() {
        return Arrays.asList(Gadget.ICON_TYPE_MINIMIZE, Gadget.ICON_TYPE_SHOWTOOLS);
    }

    @Override
    public List<String> getSupportedMenuItems() {
        return Arrays.asList(Gadget.MENU_TYPE_DELETE);
    }

}
