package EndToEndFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EndToEndFramework.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	By productNamesList= By.cssSelector("tr td:nth-child(3)");
	
	public boolean verifyOrderDisplay(String productName) {
		waitforElementToAppear(productNamesList);
		boolean match=false;
		for (WebElement product : productNames) {
			if(product.getText().equals(productName))
				match=true;
		}
		return match;
	}

}
