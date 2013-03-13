package org.infoscoop_selenium.screenshot;


import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * ログインのスクリーンショット
 * @author nishiumi
 *
 */
public class LoginScreenShot extends IS_BaseItTestCase{
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
	}

	@Test
	/**
	 * ログイン
	 */
	public void ログイン(){
		WebDriver driver = getDriver();

		// login
		driver.get(getPortal().getUrl() + "/login.jsp");
		
		TestHelper.getScreenShot("ログイン", driver);

		assertTrue(true);
	}
	
	@Test
	/**
	 * ログイン（パスワード未入力）
	 */
	public void ログイン_パスワード未入力(){
		WebDriver driver = getDriver();

		// login
		driver.get(getPortal().getUrl() + "/login.jsp");
		
		// 入力
		driver.findElement(By.id("uid")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("loginform")).submit();
		
		// エラーメッセージを待つ
//		WebElement loginForm = driver.findElement(By.className("LoginForm"));
//		loginForm.findElement(By.cssSelector("td[style=\"color: red;\"]"));
//		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[4]/td[contains(@style,'red']"));
		
		TestHelper.getScreenShot("ログイン（パスワード未入力）", driver);
		
		assertTrue(true);
	}

	@Test
	/**
	 * ログイン（パスワードエラー）
	 */
	public void ログイン_パスワードエラー(){
		WebDriver driver = getDriver();

		// login
		driver.get(getPortal().getUrl() + "/login.jsp");
		
		// 入力
		driver.findElement(By.id("uid")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("hogehoge");
		driver.findElement(By.id("loginform")).submit();
		
		// エラーメッセージを待つ
//		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[4]/td[@style='color:red;']"));
		
		TestHelper.getScreenShot("ログイン（パスワードエラー）", driver);
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * ログイン（存在しないユーザー）
	 */
	public void ログイン_存在しないユーザー(){
		WebDriver driver = getDriver();

		// login
		driver.get(getPortal().getUrl() + "/login.jsp");
		
		// 入力
		driver.findElement(By.id("uid")).sendKeys("admin10");
		driver.findElement(By.id("password")).sendKeys("hugahuga");
		driver.findElement(By.id("loginform")).submit();
		
		// エラーメッセージを待つ
//		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[4]/td[@style='color:red;']"));
		
		TestHelper.getScreenShot("ログイン（存在しないユーザー）", driver);
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * パスワード変更
	 */
	public void パスワード変更(){
		WebDriver driver = getDriver();

		// login
		driver.get(getPortal().getUrl() + "/login.jsp");
		
		// パスワード変更画面
		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[5]/td/input[@type='button']")).click();
		driver.findElement(By.id("loginform"));
		
		TestHelper.getScreenShot("パスワード変更", driver);
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * パスワード変更（パスワード未入力）
	 */
	public void パスワード変更_パスワード未入力(){
		WebDriver driver = getDriver();

		// login
		driver.get(getPortal().getUrl() + "/login.jsp");
		
		// パスワード変更画面
		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[5]/td/input[@type='button']")).click();
		driver.findElement(By.id("loginform"));
		
		// 入力
		driver.findElement(By.id("uid")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("loginform")).submit();
		
		// エラーメッセージを待つ
//		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[4]/td[@style='color:red;']"));
		
		TestHelper.getScreenShot("パスワード変更（パスワード未入力）", driver);
				
		assertTrue(true);
	}

	@Test
	/**
	 * パスワード変更（パスワードエラー）
	 */
	public void パスワード変更_パスワードエラー(){
		WebDriver driver = getDriver();

		// login
		driver.get(getPortal().getUrl() + "/login.jsp");
		
		// パスワード変更画面
		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[5]/td/input[@type='button']")).click();
		driver.findElement(By.id("loginform"));
		
		// 入力
		driver.findElement(By.id("uid")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("adminadmin");
		driver.findElement(By.id("new_password")).sendKeys("admin");
		driver.findElement(By.id("loginform")).submit();
		
		// エラーメッセージを待つ
//		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[4]/td[@style='color:red;']"));
		
		TestHelper.getScreenShot("パスワード変更（パスワードエラー）", driver);
		
		assertTrue(true);
	}

	@Test
	/**
	 * パスワード変更（存在しないユーザー）
	 */
	public void パスワード変更_存在しないユーザー(){
		WebDriver driver = getDriver();

		// login
		driver.get(getPortal().getUrl() + "/login.jsp");
		
		// パスワード変更画面
		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[5]/td/input[@type='button']")).click();
		driver.findElement(By.id("loginform"));
		
		// 入力
		driver.findElement(By.id("uid")).sendKeys("admin98");
		driver.findElement(By.id("password")).sendKeys("adminadmin");
		driver.findElement(By.id("new_password")).sendKeys("admin");
		driver.findElement(By.id("loginform")).submit();
		
		// エラーメッセージを待つ
//		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[4]/td[@style='color:red;']"));
		
		TestHelper.getScreenShot("パスワード変更（存在しないユーザー）", driver);
		
		assertTrue(true);
	}

	@Test
	/**
	 * パスワード変更（正常系）
	 */
	public void パスワード変更_正常系(){
		WebDriver driver = getDriver();

		// login
		driver.get(getPortal().getUrl() + "/login.jsp");
		
		// パスワード変更画面
		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[5]/td/input[@type='button']")).click();
		driver.findElement(By.id("loginform"));
		
		// 入力
		driver.findElement(By.id("uid")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("new_password")).sendKeys("admin");
		driver.findElement(By.id("loginform")).submit();
		
		// 成功メッセージを待つ
//		driver.findElement(By.xpath("//table[@class='LoginForm']/tbody/tr[4]/td[@style='color:red;']"));
		
		TestHelper.getScreenShot("パスワード変更（正常系）", driver);
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * 初期ロード画面
	 */
	public void 初期ロード画面(){
		WebDriver driver = getDriver();

		// login
		getPortal().login();
		
		TestHelper.getScreenShot("初期ロード画面", driver);
		
		assertTrue(true);
	}
	
}
