package org.infoscoop_selenium.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.thoughtworks.selenium.Wait;

public class TestHelper {
	private static int WAIT_SECOND = 5;

	/**
	 * 要素の出現を待つ
	 * @param context
	 * @param by
	 */
	public static void waitPresent(final SearchContext context, final By by) {
		Wait wait = new Wait() {
			@Override
			public boolean until() {
				try {
					WebElement elem = context.findElement(by);
					return elem.isDisplayed();
				} catch (NoSuchElementException e) {
					return false;
				}
			}
		};

		wait.wait("Element not exists", WAIT_SECOND * 1000);
	}
	
	/**
	 * 要素が消えるのを待つ
	 * @param context
	 * @param by
	 */
	public static void waitInvisible(final SearchContext context, final By by) {
		Wait wait = new Wait() {
			@Override
			public boolean until() {
				WebElement elem = context.findElement(by);
				return false == elem.isDisplayed();
			}
		};
		wait.wait("Element exists", WAIT_SECOND * 1000);
	}
}
