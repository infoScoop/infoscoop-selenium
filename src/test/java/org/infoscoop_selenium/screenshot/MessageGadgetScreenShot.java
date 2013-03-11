package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.MessageGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * メッセージガジェットのスクリーンショット
 * @author mikami
 *
 */
public class MessageGadgetScreenShot extends IS_BaseItTestCase {
	private static MessageGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// ガジェットのドロップ
		GADGET = (MessageGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_Message", 1, GADGET_TYPE.MESSAGE);
	}

	@Test
	/**
	 * メッセージガジェット
	 */
	public void メッセージガジェット(){
		WebDriver driver = getDriver();		
		
		TestHelper.getScreenShot("メッセージガジェット", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * メッセージガジェット（ガジェットメニュー）
	 */
	public void メッセージガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		GADGET.openMenu();
		
		TestHelper.getScreenShot("メッセージガジェット（ガジェットメニュー）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * メッセージガジェット（グループ編集）
	 */
	public void メッセージガジェット_グループ編集(){
		WebDriver driver = getDriver();

		// グループ編集画面を開く
		GADGET.openGroupEdit();
		
		TestHelper.getScreenShot("メッセージガジェット（グループ編集）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * メッセージガジェット（グループ編集／グループ名編集）
	 */
	public void メッセージガジェット_グループ編集_グループ名編集(){
		WebDriver driver = getDriver();
		
		// グループ編集画面を開く
		GADGET.openGroupEdit();
		
		// グループ名編集ボタンをクリック
		driver.findElement(By.xpath("//ul[@id='group-setting--tabs']/li[1]/span/span[@class='icon edit']")).click();
		
		TestHelper.getScreenShot("メッセージガジェット（グループ編集／グループ名編集）", driver);
	
		assertTrue(true);
	}

	@Test
	/**
	 * メッセージガジェット（メッセージ投稿）
	 */
	public void メッセージガジェット_メッセージ投稿(){
		WebDriver driver = getDriver();

		//公開メッセージの送信
		GADGET.sendPublicMessage("メッセージテスト", GADGET.getContents());
		
		TestHelper.getScreenShot("メッセージガジェット（メッセージ投稿）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * メッセージガジェット（最大化）
	 */
	public void メッセージガジェット_最大化(){
		WebDriver driver = getDriver();
		
		// 最大化
		GADGET.maximaize();

		// 公開メッセージの送信
		GADGET.sendPublicMessage("メッセージテスト", GADGET.getMaximaizeContentsMenu());

		// 全ユーザの公開メッセージを開く
		GADGET.getMaximaizeContentsMenu().findElement(By.xpath("//ul[@class='messageUsers']/li[4]/a")).click();
		
		TestHelper.getScreenShot("メッセージガジェット（最大化）", driver);
	
		assertTrue(true);
	} 
	
}
