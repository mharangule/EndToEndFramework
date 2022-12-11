package EndToEndFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EndToEndFramework.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(xpath = "//li[contains(@class,'Row')]/button")
	WebElement checkout;

	public boolean verifyProdcutDisplay(String productName) {
		boolean isProductPresent = false;
		List<WebElement> products = cartProducts;
		for (WebElement product : products) {
			if (product.getText().equalsIgnoreCase(productName))
				isProductPresent = true;
			else
				isProductPresent = false;
		}
		return isProductPresent;
	}

	public CheckOutPage checkout() {
		checkout.click();
		return new CheckOutPage(driver);
	}
}
