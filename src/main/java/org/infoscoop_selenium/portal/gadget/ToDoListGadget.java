package org.infoscoop_selenium.portal.gadget;

import java.util.Arrays;
import java.util.List;

import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ToDoListGadget extends Gadget{
	public ToDoListGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}
	
	public static enum PRIORITY {
		HIGH(0, "高", "rgba(255, 0, 0, 1)"),
		MIDDLE(1, "中", "rgba(0, 153, 0, 1)"),
		LOW(2, "低", "rgba(34, 34, 136, 1)");

		private final int priority;
		private final String text;
		private final String cssColor;
		private PRIORITY(int priority, String text, String cssColor){
			this.priority = priority;
			this.text = text;
			this.cssColor = cssColor;
		}
		public int getValue(){
			return priority;
		}
		public String getText() {
		    return text;
		}
		public String getCssColor() {
		    return cssColor;
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
		super.focus();
		driver.findElement(By.id(super.getId()+"_inputText")).sendKeys(msg);
		driver.findElement(By.xpath("//table[@class='todoAddTable']/tbody/tr/td[2]/input[@type='button']")).click();		
		super.blur();
	}
	
	/**
	 * ToDoをエンターキーで追加
	 * @param msg
	 */
	public void addToDoByEnter(String msg){
		super.focus();
		WebElement inputText = driver.findElement(By.id(super.getId()+"_inputText"));
		inputText.sendKeys(msg);
		
		inputText.sendKeys(Keys.RETURN);
		super.blur();
	}

	/**
	 * 優先度の変更
	 * @param order (ToDoの上から数えての順番)
	 * @param priority
	 */
	public void changePriority(int order, PRIORITY priority){
		super.focus();
		driver.findElement(By.xpath("//tbody[@id='"+super.getId()+"_list']/tr["+order+"]/td[@class='todoPriorityTd']/div")).click();
		Select select = new Select(driver.findElement(By.xpath("//tbody[@id='"+super.getId()+"_list']/tr["+order+"]/td[@class='todoPriorityTd']/select")));
		select.selectByIndex(priority.getValue());
		super.blur();		
	}
	
	/**
	 * チェックする
	 * @param order (ToDoの上から数えての順番)
	 */
	public void checkToDo(int order){
		super.focus();
		driver.findElement(By.xpath("//tbody[@id='"+super.getId()+"_list']/tr["+order+"]/td[@class='todoCheckTd']/input")).click();
		super.blur();		
	}
	
	/**
	 * 削除する
	 * @param order (ToDoの上から数えての順番)
	 */
	public void deleteToDo(int order){
		super.focus();
		driver.findElement(By.xpath("//tbody[@id='"+super.getId()+"_list']/tr["+order+"]/td[@class='todoDeleteTd']/img")).click();
		super.blur();		
	}
	
	/**
	 * フォントサイズの変更
	 * @param fontSize
	 */
	public void changeFontSize(FONTSIZE fontSize) {
		getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		Select font = new Select(driver.findElement(By.cssSelector("#eb_" + super.getId() + "_fontSize>select")));
		font.selectByValue(fontSize.getValue());
		
		// ガジェット設定を閉じる
		getGadgetPreference().ok();
	}

	/**
	 * TODOテキストを変更する。
	 * @param order
	 * @param msg
	 */
    public void changeTodoText(int order, String msg) {
        super.focus();
        WebElement todoTextTd = driver.findElement(By.cssSelector(
                ".todoListTable tr:nth-of-type(" + order + ") .todoTextTd"));
        WebElement todoText = todoTextTd.findElement(By.cssSelector(".todoText"));
        todoText.click();
        WebElement todoTextEdit = todoTextTd.findElement(By.tagName("input"));
        todoTextEdit.sendKeys(msg, Keys.ENTER);
        super.blur();
    }

    /**
     * 優先度テキストを表示している要素を返す。
     * @param order
     * @return
     */
    public WebElement getTodoPriorityElement(int order) {
        super.focus();
        List<WebElement> childNodes = driver.findElements(By.cssSelector(
                ".todoListTable tr:nth-of-type(" + order + ") .todoPriorityTd > *"));
        WebElement todoPriority = null;
        if (0 < childNodes.size()
                && "todoPriority".equals(childNodes.get(0).getAttribute("class"))) {
            todoPriority = childNodes.get(0);
        }
        super.blur();
        return todoPriority;
    }

    /**
     * 削除のチェックが入っているか否かを返す。
     * @param order
     * @return
     */
    public boolean getCheckState(int order) {
        WebElement todoRow = getTodoRowElement(order);
        super.focus();
        WebElement todoCheck = todoRow.findElement(By.className("todoCheck"));
        
        boolean checked = todoCheck.isSelected();
        
        super.blur();
        return checked;
    }

    /**
     * 優先度のセレクトボックスを返す。
     * @param order
     * @return
     */
    public WebElement getChangePrioritySelectBoxElement(int order) {
        super.focus();
        List<WebElement> childNodes = driver.findElements(By.cssSelector(
                ".todoListTable tr:nth-of-type(" + order + ") .todoPriorityTd > *"));
        WebElement selectBox = null;
        for (WebElement element : childNodes) {
            if ("select".equals(element.getTagName()))
                selectBox = element;
        }
        super.blur();
        return selectBox;
    }

    /**
     * TODOテキストエレメントを返す。
     * @param order
     * @return 
     */
    public WebElement getTodoTextElement(int order) {
        super.focus();
        List<WebElement> childNodes = driver.findElements(By.cssSelector(
                ".todoListTable tr:nth-of-type(" + order + ") .todoTextTd > *"));
        WebElement todoText = null;
        for (WebElement element : childNodes) {
            if ("todoTextDiv".equals(element.getAttribute("class")))
                todoText = element.findElement(By.className("todoText"));
        }
        
        System.out.println(todoText);
        super.blur();
        return todoText;
    }

    /**
     * TODOテキストを返す。
     * @param order
     * @return 
     */
    public String getTodoText(int order) {
        String todoMsg = "";
        WebElement el = getTodoTextElement(order);
        super.focus();
        todoMsg = el.getText();
        super.blur();
        return todoMsg;
    }

    /**
     * TODOテキスト編集エレメントを返す。
     * @param order
     * @return 
     */
    public WebElement getTodoTextEditElement(int order) {
        super.focus();
        List<WebElement> childNodes = driver.findElements(By.cssSelector(
                ".todoListTable tr:nth-of-type(" + order + ") .todoTextTd > *"));
        WebElement todoTextEdit = null;
        for (WebElement element : childNodes) {
            if ("todoTextEdit".equals(element.getAttribute("class")))
                todoTextEdit = element;
        }
        super.blur();
        return todoTextEdit;
    }

	/**
	 * TODO追加テキストフォームを返す
	 * @return
	 */
	public WebElement getTodoAddTextBoxElement(){
		super.focus();
		WebElement textBox = driver.findElement(By.cssSelector(".todoAddTextBox"));
		super.blur();
		
		return textBox;
	}

	/**
	 * TODO追加ボタンを返す
	 * @return
	 */
	public WebElement getTodoAddButtonElement(){
		super.focus();
		WebElement addButton =  driver.findElement(By.cssSelector(".todoAddTable input[type=\"button\"]"));
		super.blur();
		
		return addButton;
	}

	/**
	 * TODOの行要素を返す
	 * @return
	 */
	public WebElement getTodoRowElement(int order){
		super.focus();
		List<WebElement> rows = driver.findElements(By.cssSelector(".todoListTable tr"));
		WebElement row  = rows.get(order-1);
		super.blur();
		
		return row;
	}
	
	/**
	 * TODO登録数を返す
	 * @return
	 */
	public int getTodoLength(){
		int len;
		
		super.focus();
		len = driver.findElements(By.cssSelector(".todoListTable tr")).size();
		super.blur();
		
		return len;
	}

	/**
	 * フォントサイズを返す。
	 * UserPrefの設定値ではなく、DOMノードの高さをpixelで返す
	 * @return
	 */
	public int getDisplayFontSize(){
		if(getTodoLength() == 0)
			addToDo("test");
		
		super.focus();
		int displayFontSize = driver.findElement(By.className("todoText")).getSize().height;
		super.blur();
		return displayFontSize;
	}
	
	@Override
	public List<String> getSupportedHeaderIcons() {
		return Arrays.asList(Gadget.ICON_TYPE_MINIMIZE, Gadget.ICON_TYPE_SHOWTOOLS);
	}

	@Override
	public List<String> getSupportedMenuItems() {
		return Arrays.asList(Gadget.MENU_TYPE_EDIT, Gadget.MENU_TYPE_DELETE);
	}

    @Override
    public List<ProperHeaderIcon> getProperHeaderIconList() {
        return null;
    }

}
