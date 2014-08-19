package org.infoscoop_selenium.portal.gadget;

import java.util.List;

import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalculatorGadget extends Gadget{
	public CalculatorGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}
	
	public static enum KEY {
		ADD("add"),
		SUBSTRACT("subtract"),
		MULTIPLY("multiply"),
		DIVIDE("divide"),
		EQUAL("equal"),
		DOT("dot"),
		AC("allClear");
		private final String key;
		private KEY(String key){
			this.key = key;
		}
		public String getValue(){
			return key;
		}
	}
	
	/**
	 * 演算ボタン選択
	 * @param msg
	 */
	public void selectOperationButton(KEY key){
		super.focus();
		driver.findElement(By.id(key.getValue())).click();;
		super.blur();
	}
	
	/**
	 * 数字ボタン選択
	 */
	public void selectNumberButton(String num) {
		super.focus();
		for(int i=0;i<num.length();i++ ) {
			String key = null;
			char c = num.charAt(i);
			String s = String.valueOf(c);
			if( s.equals(".") ) {
				key = KEY.DOT.getValue();
			}
			else if( s.equals("-") ) {
				key = KEY.SUBSTRACT.getValue();
			}
			else {
				int n = Integer.parseInt(s);
				if( 0 <= n && n < 10 )
					key = "num" + s;
			}
			driver.findElement(By.id(key)).click();;
		}
		super.blur();
	}
	
	/**
	 * キー入力
	 */
	public void inputKey(Number keyCode) {
	}
	
	/**
	 * 結果取得
	 * @param msg
	 */
	public String getResult(){
		super.focus();
		String result = driver.findElement(By.id("display")).getAttribute("value");
		super.blur();
		return result;
	}
	
	
	/**
	 * 演算子取得
	 * @param msg
	 */
	public String getOperation(){
		super.focus();
		String result = driver.findElement(By.id("operation")).getText();
		super.blur();
		return result;
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
