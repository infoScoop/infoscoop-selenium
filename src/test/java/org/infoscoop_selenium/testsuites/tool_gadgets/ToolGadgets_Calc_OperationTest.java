package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.*;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.CalculatorGadget;
import org.infoscoop_selenium.portal.gadget.CalculatorGadget.KEY;
import org.junit.Test;

/**
 * ツール系ガジェット/電卓/演算
 */
public class ToolGadgets_Calc_OperationTest extends IS_BaseItTestCase{
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
	 * 0/0
	 */
	public void iscp_4059(){
		GADGET.selectNumberButton("0");
		GADGET.selectOperationButton(KEY.DIVIDE);
		GADGET.selectNumberButton("0");
		GADGET.selectOperationButton(KEY.EQUAL);
		
		assertEquals(GADGET.getResult(), "NaN");
		
		GADGET.selectOperationButton(KEY.MULTIPLY);
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "NaN");
	}
	
	@Test
	/**
	 * n/0 ( n != 0 )
	 */
	public void iscp_4060(){
		GADGET.selectNumberButton("1");
		GADGET.selectOperationButton(KEY.DIVIDE);
		GADGET.selectNumberButton("0");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "Infinity");
		
		GADGET.selectOperationButton(KEY.AC);
		GADGET.selectNumberButton("-1");
		GADGET.selectOperationButton(KEY.DIVIDE);
		GADGET.selectNumberButton("0");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "-Infinity");
	}
	
	@Test
	/**
	 * 演算結果が大きな数字
	 */
	public void iscp_4061(){
		GADGET.selectNumberButton("1234567898.7654");
		GADGET.selectOperationButton(KEY.MULTIPLY);
		GADGET.selectNumberButton("1000000000");
		GADGET.selectOperationButton(KEY.EQUAL);
		assertEquals(GADGET.getResult(), "1.2345678987654e+18");
	}
}
