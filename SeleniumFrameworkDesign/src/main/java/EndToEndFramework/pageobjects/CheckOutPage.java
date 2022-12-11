package EndToEndFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EndToEndFramework.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-results")
	WebElement searchResultSection;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement desiredCountry;

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submitOrderButton;

	By results = By.cssSelector(".ta-results");
	By submitorder = By.xpath("//a[@class='btnn action__submit ng-star-inserted']");

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitforElementToAppear(results);
		desiredCountry.click();
	}

	public ConfirmationPage submitOrder() {
		waitForElementToBEClickable(submitOrderButton);
		JavascriptExecutor je =(JavascriptExecutor)driver;
		je.executeScript("arguments[0].click()",submitOrderButton );
		return new ConfirmationPage(driver);
	}

}
