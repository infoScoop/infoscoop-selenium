package org.infoscoop_selenium.portal.gadget;

import java.util.List;

import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.WebDriver;

public class GenericGadget extends Gadget{

	public GenericGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
		// TODO Auto-generated constructor stub
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
