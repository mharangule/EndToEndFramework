package EndToEndFramework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import EndToEndFramework.TestComponents.BaseTest;
import EndToEndFramework.pageobjects.CartPage;
import EndToEndFramework.pageobjects.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest {
	@Test (groups= {"ErroHandling"}) // retryAnalyzer=Retry.class
	public void validateMesssage() {
		landingPage.loginApplication("maheshharangule02@gmail.com", "Mahi@22");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() {
		String productName = "ZARA COAT 3";
		ProductCatalouge productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertFalse(cartPage.verifyProdcutDisplay("ZARA COAT 33"));
		
	}
}
