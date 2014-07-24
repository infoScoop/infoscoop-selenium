package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.junit.Test;

public class ToolGadgets_Sticky_InputTest extends IS_BaseItTestCase {
	private static StickyGadget GADGET;	

	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		GADGET = (StickyGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_stickey", 1, GADGET_TYPE.STICKY);
	}

	@Test
	/**
	 * 入力
	 */
	public void iscp_4087(){
		String inputStr = "メッセージ";
		GADGET.writeSticky("\b\b\b\b\b\b\b"+inputStr, false);
		String content = GADGET.getContent();
		assertEquals(inputStr, content);
	}

	//@Test
	/**
	 * 折り返し（設定値がシングルバイト）
	 */
	public void iscp_7248(){
		
	}

	@Test
	/**
	 * 特殊文字
	 */
	public void iscp_4088(){
		String inputStr = "①㈱㌢<>&\"'!\"#$%'()~=-^@`'*+;:[{]}\\_/?<>\\.タダイマポチゼンブアソボゾウノファミリ―～∥－￤";
		GADGET.writeSticky("\b\b\b\b\b\b\b"+inputStr, false);
		String content = GADGET.getContent();
		assertEquals(inputStr, content);
	}

	@Test
	/**
	 * 境界値
	 */
	public void iscp_4089(){
		//4099文字の文字列を作成
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=256; i++) {
			sb.append("0123456789abcdef");
		}
		String inputStr = sb.toString() + "ABC";
		GADGET.writeSticky("\b\b\b\b\b\b\b"+inputStr, false);
		String content = GADGET.getContent();
		assertEquals(inputStr, content);
	}

	@Test
	/**
	 * URL
	 */
	public void iscp_4090(){
		String inputStr = "http://www.beacon-it.co.jp/t/n/r";
		GADGET.writeSticky("\b\b\b\b\b\b\b"+inputStr, false);
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();
		
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
		String content = GADGET.getContent();
		assertEquals(inputStr, content);
	}

	//@Test
	/**
	 * タブ文字
	 * @XXX タブ文字が入力できない。
	 */
	public void iscp_4091(){
		String inputStr = "タブ\t文字";
		GADGET.writeSticky("\b\b\b\b\b\b\b"+inputStr, false);
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();
		
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
		String content = GADGET.getContent();
		assertEquals(inputStr, content);
	}

	@Test
	/**
	 * 改行での高さ調整
	 */
	public void iscp_4092(){
		int height0 = GADGET.getGadgetElement().getSize().getHeight();
		String inputStr = "メッセージ";
		GADGET.writeSticky("\b\b\b\b\b\b\b"+inputStr, true);
		int height1 = GADGET.getGadgetElement().getSize().getHeight();
		assertTrue(height0+"<"+height1, height0 < height1);
	}

	@Test
	/**
	 * 折り返しでの高さ調整
	 */
	public void iscp_4093(){
		int height0 = GADGET.getGadgetElement().getSize().getHeight();
		//100文字の文字列を作成
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=10; i++) {
			sb.append("0123456789");
		}
		String inputStr = sb.toString();
		GADGET.writeSticky("\b\b\b\b\b\b\b"+inputStr, false);
		int height1 = GADGET.getGadgetElement().getSize().getHeight();
		assertTrue(height0+"<"+height1, height0 < height1);
	}

	@Test
	/**
	 * 保存確認
	 */
	public void iscp_4094(){
		String inputStr = "メッセージ";
		GADGET.writeSticky("\b\b\b\b\b\b\b"+inputStr, false);
		
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
		String content = GADGET.getContent();
		assertEquals("クリックで入力", content);
	}
}
