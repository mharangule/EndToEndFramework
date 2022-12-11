package EndToEndFramework.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EndToEndFramework.TestComponents.BaseTest;
import EndToEndFramework.pageobjects.CartPage;
import EndToEndFramework.pageobjects.CheckOutPage;
import EndToEndFramework.pageobjects.ConfirmationPage;
import EndToEndFramework.pageobjects.OrdersPage;
import EndToEndFramework.pageobjects.ProductCatalouge;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException {
		String countryName = "India";
		ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalouge.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalouge.goToCartPage();
		Assert.assertTrue(cartPage.verifyProdcutDisplay(input.get("product")));
		CheckOutPage checkOutPage = cartPage.checkout();
		checkOutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalouge productCatalouge = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrdersPage ordersPage = productCatalouge.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//EndToEndFramework//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	/*
	 * Alternate Implementation
	 * 
	 * @DataProvider public Object[][] getData() { return new Object [][]
	 * {{"maheshharangule02@gmail.com","Mahi@22748081","ZARA COAT 3"},
	 * {"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}} ; }
	 */

	/*
	 * HashMap<String,String> map=new HashMap<String, String>(); map.put("email",
	 * "maheshharangule02@gmail.com"); map.put("password", "Mahi@22748081");
	 * map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1=new HashMap<String, String>(); map1.put("email",
	 * "anshika@gmail.com"); map1.put("password", "Iamking@000");
	 * map1.put("product", "ADIDAS ORIGINAL");
	 */
}
