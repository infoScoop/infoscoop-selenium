package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.CalculatorGadget;
import org.infoscoop_selenium.portal.gadget.CalculatorGadget.KEY;
import org.junit.Test;

/**
 * ツール系ガジェット/電卓/演算/ボタン入力
 */
public class ToolGadgets_Calc_Operation_ButtonTest extends IS_BaseItTestCase{
	private static CalculatorGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		GADGET = (CalculatorGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_calculator", 1, GADGET_TYPE.CALCULATOR);
	}
	
	@Test
	/**
	 * ボタンクリックによる数字入力。"01"の場合"1"となることも確認。
	 */
	public void iscp_4062(){
		GADGET.selectNumberButton("0123456789");
		assertEquals(GADGET.getResult(), "123456789");
	}
	
	@Test
	/**
	 * "."ボタンをクリックすると、表示している数字に小数点が追加されることを確認
	 */
	public void iscp_4063(){
		GADGET.selectNumberButton("1.");
		assertEquals(GADGET.getResult(), "1.");
	}
	
	@Test
	/**
	 * "."ボタンをクリックすると、表示している数字に小数点が追加されることを確認
	 */
	public void iscp_4064(){
		GADGET.selectNumberButton("1..");
		assertEquals(GADGET.getResult(), "1.");
		GADGET.selectNumberButton("2.");
		assertEquals(GADGET.getResult(), "1.2");
	}
	
	@Test
	/**
	 * 演算ボタン（+,-,*,/）をクリックしてテキストボックス右に演算子が表示されることを確認。
	 */
	public void iscp_4065(){
		GADGET.selectOperationButton(KEY.ADD);
		assertEquals(GADGET.getOperationText(), "+");
		GADGET.selectOperationButton(KEY.SUBSTRACT);
		assertEquals(GADGET.getOperationText(), "-");
		GADGET.selectOperationButton(KEY.MULTIPLY);
		assertEquals(GADGET.getOperationText(), "*");
		GADGET.selectOperationButton(KEY.DIVIDE);
		assertEquals(GADGET.getOperationText(), "/");
	}
	
	@Test
	/**
	 * 演算ボタンをクリック後、別の演算ボタンをクリックすると、右上の演算子のみが更新され、テキストボックスの数値には変化がないことを確認
	 */
	public void iscp_4066(){
		GADGET.selectNumberButton("1");
		assertEquals(GADGET.getResult(), "1");
		GADGET.selectOperationButton(KEY.ADD);
		assertEquals(GADGET.getResult(), "1");
		GADGET.selectOperationButton(KEY.SUBSTRACT);
		assertEquals(GADGET.getResult(), "1");
		GADGET.selectOperationButton(KEY.MULTIPLY);
		assertEquals(GADGET.getResult(), "1");
		GADGET.selectOperationButton(KEY.DIVIDE);
		assertEquals(GADGET.getResult(), "1");
	}
	
	@Test
	/**
	 * 演算ボタンをクリック後、数字ボタン又は "."ボタンをクリックすると、表示中の数値がリセットされて新しく数字が表示されることを確認
	 */
	public void iscp_4067() {
		ArrayList<KEY> keys = new ArrayList<KEY>();
		keys.add(KEY.ADD);
		keys.add(KEY.SUBSTRACT);
		keys.add(KEY.MULTIPLY);
		keys.add(KEY.DIVIDE);
		
		// 演算ボタン選択後に数字入力
		for( int i=0;i<keys.size();i++ ) {
			GADGET.selectNumberButton("1");
			assertEquals(GADGET.getResult(), "1");
			GADGET.selectOperationButton(keys.get(i));
			GADGET.selectNumberButton("2");
			assertEquals(GADGET.getResult(), "2");
			GADGET.selectOperationButton(KEY.AC);
		}
		
		// 演算ボタン選択後に"."入力
		for( int i=0;i<keys.size();i++ ) {
			GADGET.selectNumberButton("1");
			assertEquals(GADGET.getResult(), "1");
			GADGET.selectOperationButton(keys.get(i));
			GADGET.selectNumberButton(".");
			assertEquals(GADGET.getResult(), "0.");
			GADGET.selectOperationButton(KEY.AC);
		}
	}
	
	@Test
	/**
	 * （数字１）→（演算ボタン）→（数字２）→（＝ボタン）の順に入力すると、テキストボックス右上に表示中の演算子による数字１と２の演算結果が表示されることを確認
	 */
	public void iscp_4068() {
		// 加算
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "2");
		GADGET.selectOperationButton(KEY.AC);
		
		// 減算
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.SUBSTRACT);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "2");
		GADGET.selectOperationButton(KEY.AC);
		
		// 乗算
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.MULTIPLY);
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "6");
		GADGET.selectOperationButton(KEY.AC);
		
		// 除算
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.DIVIDE);
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "1.5");
	}
	
	@Test
	/**
	 * 繰り返し計算
	 * 計算後に、数字ボタン又は \".\"ボタンをクリックすると、計算がリセットされて新しい計算が行われることを確認。テキストボックス右上の演算子も消える。
	 */
	public void iscp_4069() {
		// 加算後に整数入力
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "2");
		GADGET.selectNumberButton("1");
		assertEquals(GADGET.getResult(), "1");
		assertEquals(GADGET.getOperationText(), "");
		GADGET.selectOperationButton(KEY.AC);
		
		// 加算後に"."入力
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "2");
		GADGET.selectNumberButton(".");
		assertEquals(GADGET.getResult(), "0.");
		assertEquals(GADGET.getOperationText(), "");
		GADGET.selectOperationButton(KEY.AC);
		
		// 減算後に整数入力
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.SUBSTRACT);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "2");
		GADGET.selectNumberButton("1");
		assertEquals(GADGET.getResult(), "1");
		assertEquals(GADGET.getOperationText(), "");
		GADGET.selectOperationButton(KEY.AC);
		
		// 減算後に"."入力
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.SUBSTRACT);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "2");
		GADGET.selectNumberButton(".");
		assertEquals(GADGET.getResult(), "0.");
		assertEquals(GADGET.getOperationText(), "");
		GADGET.selectOperationButton(KEY.AC);
		
		// 乗算後に整数入力
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.MULTIPLY);
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "6");
		GADGET.selectNumberButton("1");
		assertEquals(GADGET.getResult(), "1");
		assertEquals(GADGET.getOperationText(), "");
		GADGET.selectOperationButton(KEY.AC);
		
		// 乗算後に"."入力
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.MULTIPLY);
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "6");
		GADGET.selectNumberButton(".");
		assertEquals(GADGET.getResult(), "0.");
		assertEquals(GADGET.getOperationText(), "");
		GADGET.selectOperationButton(KEY.AC);
		
		
		// 除算後に整数入力
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.DIVIDE);
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "1.5");
		GADGET.selectNumberButton("1");
		assertEquals(GADGET.getResult(), "1");
		assertEquals(GADGET.getOperationText(), "");
		GADGET.selectOperationButton(KEY.AC);
		
		// 除算後に"."入力
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.DIVIDE);
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "1.5");
		GADGET.selectNumberButton(".");
		assertEquals(GADGET.getResult(), "0.");
		assertEquals(GADGET.getOperationText(), "");
		GADGET.selectOperationButton(KEY.AC);
	}
	
	@Test
	/**
	 * 繰り返し計算
	 * 計算後に、演算ボタンをクリックすると、演算結果の続きの計算が行われることを確認
	 */
	public void iscp_4070() {
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "2");
		
		// 続けて加算
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "3");
		
		// 続けて減算
		GADGET.selectOperationButton(KEY.SUBSTRACT);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "2");
		
		// 続けて乗算
		GADGET.selectOperationButton(KEY.MULTIPLY);
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "6");
		
		// 続けて除算
		GADGET.selectOperationButton(KEY.DIVIDE);
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "3");
	}
	
	@Test
	/**
	 * 繰り返し計算
	 * 計算後に、＝ボタンをクリックすると、上記の操作での計算が繰り返し行われることを確認
	 */
	public void iscp_4071() {
		// 続けて加算
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "3");
		
		// 続けて減算
		GADGET.selectNumberButton("3");
		GADGET.selectOperationButton(KEY.SUBSTRACT);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "1");
		
		// 続けて乗算
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.MULTIPLY);
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.EQUAL);
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "8");
		
		// 続けて除算
		GADGET.selectNumberButton("8");
		GADGET.selectOperationButton(KEY.DIVIDE);
		GADGET.selectNumberButton("2");
		GADGET.selectOperationButton(KEY.EQUAL);
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "2");
	}
	
	@Test
	/**
	 * 演算の表示
	 * （数字１）→（演算ボタン１）→（数字２）→（演算ボタン２）の順に入力すると、演算ボタン１による数字１と２の演算結果が表示されて、テキストボックスの右上には演算ボタン２が表示されることを確認
	 */
	public void iscp_4072() {
		// "+"表示
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		assertEquals(GADGET.getResult(), "2");
		assertEquals(GADGET.getOperationText(), "+");
		GADGET.selectOperationButton(KEY.AC);
		
		// "-"表示
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.SUBSTRACT);
		assertEquals(GADGET.getResult(), "2");
		assertEquals(GADGET.getOperationText(), "-");
		GADGET.selectOperationButton(KEY.AC);
		
		// "*"表示
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.MULTIPLY);
		assertEquals(GADGET.getResult(), "2");
		assertEquals(GADGET.getOperationText(), "*");
		GADGET.selectOperationButton(KEY.AC);
		
		// "/"表示
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.DIVIDE);
		assertEquals(GADGET.getResult(), "2");
		assertEquals(GADGET.getOperationText(), "/");
	}
	
	@Test
	/**
	 * 大きな数値の計算
	 * 以下の演算結果が正しく表示されることを確認。
	 * 数字1 = 12345678987654, 演算子: *,  数字2 = 1000000000
	 */
	public void iscp_4073() {
		GADGET.selectNumberButton("12345678987654");
		GADGET.selectOperationButton(KEY.MULTIPLY);
		GADGET.selectNumberButton("1000000000");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "1.2345678987654e+22");
		
	}
	
	@Test
	/**
	 * クリアボタン
	 * ACボタンをクリックすると、リセットされて初期表示状態に戻ることを確認。
	 */
	public void iscp_4074() {
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.ADD);
		assertEquals(GADGET.getResult(), "2");
		assertEquals(GADGET.getOperationText(), "+");
		GADGET.selectOperationButton(KEY.AC);
		assertEquals(GADGET.getResult(), "0");
		assertEquals(GADGET.getOperationText(), "");
	}
}
