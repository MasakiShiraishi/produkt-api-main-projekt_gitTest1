package com.example.produktapi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProduktapiApplicationTests {

	@Test
	@Disabled//java.lang.IllegalStateException
	public void webShopTitle() {
		// Skapa en instans av Chrome-webbläsaren med Selenium
		//System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\bin\\msedgedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Gå till webbplatsen som ska testas
		driver.get("https://java22.netlify.app/");

		// Kontrollera att webbplatsens titel stämmer
		String expectedTitle = "Webbutik";
		String actualTitle = driver.getTitle();
		if (expectedTitle.equals(actualTitle)) {
			System.out.println("Webbplatsens titel stämmer");
		} else {
			System.out.println("Webbplatsens titel stämmer inte");
		}

		// Kolla att det totala antalet produkter stämmer
		List<WebElement> products = driver.findElements(By.className("productItem"));
		int expectedProductCount = 20;
		System.out.println(products);
		int actualProductCount = products.size();
		if (expectedProductCount == actualProductCount) {
			System.out.println("Antalet produkter stämmer");
		} else {
			System.out.println("Antalet produkter stämmer inte");
		}
		driver.quit();
	}
	@Test
	@Disabled//java.lang.IllegalStateException
	public void checkIfTheThreeProductsPriceIsRight() {
		//System.setProperty("webdriver.chrom.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		WebDriver driver = new EdgeDriver();

		driver.get("https://java22.netlify.app/");
		//List<WebElement> waiter = new WebDriverWait(driver, java.time.Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));
		WebElement product1 = new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("/html/body/div/div/div[6]/div/div/div/div[1]/div/div/p")));
		WebElement product2 = new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("/html/body/div/div/div[6]/div/div/div/div[2]/div/div/p")));
		WebElement product3 = new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("/html/body/div/div/div[6]/div/div/div/div[3]/div/div/p")));
		String text = product1.getText();
		String price10995 = text.replaceAll("[^0-9.]", "");
		String text2 = product2.getText();
		String price223 = text2.replaceAll("[^0-9.]", "");
		String text3 = product3.getText();
		String price5599 = text3.replaceAll("[^0-9.]", "");

		assertEquals("109.95", price10995);
		assertEquals("22.3", price223);
		assertEquals("55.99", price5599);
		System.out.println("Price is: " + "\n" + price10995 + "\n" + price223 + "\n" + price5599);

		List<WebElement> productsPrices = new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.className("card-text")));
		for (WebElement productsPrice : productsPrices) {
			System.out.println(productsPrice);
		}
		driver.quit();
	}
/*
	@Test  //java.lang.IllegalStateException
	public void checkIfTheBackPackPriceIsRight() {
		System.setProperty("webdriver.chrom.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://java22.netlify.app/");

		WebElement waiter = new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("/html/body/div/div/div[6]/div/div/div/div[1]/div/div/p")));
		String text = waiter.getText();
		String digits = text.replaceAll("[^0-9.]", "");

		assertEquals("109.95", digits);
		System.out.println("TEST is text: " + text);
		System.out.println("Price is: " + digits);
		driver.quit();
	}

	@Test  //org.openqa.selenium.SessionNotCreatedException
	public void checkIfTheBackPackPriceIsRight_withaddArguments() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--no-sandbox"); // Bypass OS security model
		WebDriver driver = new ChromeDriver(options);

		driver.get("https://java22.netlify.app/");

		WebElement waiter = new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("/html/body/div/div/div[6]/div/div/div/div[1]/div/div/p")));
		String text = waiter.getText();
		String digits = text.replaceAll("[^0-9.]", "");

		assertEquals("109.95", digits);
		System.out.println("TEST is text: " + text);
		System.out.println("Price is: " + digits);
		driver.quit();
	}*/




	@Test
	@Disabled
	public void testCategoriesTotal_withForEachLoop(){
		WebDriver driver = new ChromeDriver();
		driver.get("https://java22.netlify.app/");

		List<WebElement> categoryElements = driver.findElements(By.className("menuLink"));

		for (WebElement categoryElement : categoryElements) {
			System.out.println(categoryElement.getText());
			assertEquals(4, categoryElements.size());
		}
		driver.quit();
	}
	@Test
	@Disabled
	public void testCategories_withMenuLink() {
		//System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://java22.netlify.app/");

		List<WebElement> categoryElements =
				driver.findElements(By.className("menuLink"));
		System.out.println(categoryElements);
		List<String> actualCategoryNames = new ArrayList<String>();
		for (WebElement categoryElement : categoryElements) {
			actualCategoryNames.add(categoryElement.getText());
		}

		List<String> expectedCategoryNames = Arrays.asList("electronics", "jewelery", "men's clothing", "women's clothing");

		assertEquals(expectedCategoryNames, actualCategoryNames);

		driver.quit();
	}
	@Test
	@Disabled
	public void testCategories_withEveryByXpath() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://java22.netlify.app/");

		List<WebElement> categoryElectronics = driver.findElements(By.xpath("/html/body/div/div/div[2]/a"));
		List<WebElement> categoryJewelery = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/a"));
		List<WebElement> categoryMens = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[4]/a"));
		List<WebElement> categoryWomens = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[5]/a"));

		List<String> actualCategoryNames = new ArrayList<String>();
		for (WebElement categoryElement : categoryElectronics) {
			actualCategoryNames.add(categoryElement.getText());
		}
		for (WebElement categoryElement : categoryJewelery) {
			actualCategoryNames.add(categoryElement.getText());
		}
		for (WebElement categoryElement : categoryMens) {
			actualCategoryNames.add(categoryElement.getText());
		}
		for (WebElement categoryElement : categoryWomens) {
			actualCategoryNames.add(categoryElement.getText());
		}

		List<String> expectedCategoryNames = Arrays.asList("electronics", "jewelery", "men's clothing", "women's clothing");

		assertEquals(expectedCategoryNames, actualCategoryNames);
		System.out.println(actualCategoryNames);
		driver.quit();
	}

	@Test
	@Disabled
	public void verifyProductPrices() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://java22.netlify.app/");

		List<WebElement> products = driver.findElements(By.className("card"));

		for (WebElement product : products) {
			WebElement titleElement = product.findElement(By.className("card-title"));
			String title = titleElement.getText();

			WebElement priceElement = product.findElement(By.className("card-text"));
			String priceText = priceElement.getText();
			double price = Double.parseDouble(priceText.replaceAll("[^0-9.]+", ""));
			double price2 = Double.parseDouble(priceText.replaceAll("[^0-9.]+", "").replaceFirst("\\.", ""));

			if (title.contains("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")) {
				assertEquals(109.95, price, 0.01);
			} else if (title.contains("Mens Casual Premium Slim Fit T-Shirts")) {
				assertEquals(22.30, price, 0.01);
			} else if (title.contains("Mens Cotton Jacket")) {
				assertEquals(55.99, price, 0.01);
			} else if (title.contains("Mens Casual Slim Fit")) {
				assertEquals(15.99, price, 0.01);
			} else if (title.contains("John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet")) {
				assertEquals(695.00, price2, 0.01);
			} else if (title.contains("SolGold Petite Micropave")) {
				assertEquals(168.00, price2, 0.01);
			}  else {fail("Unexpected product title: " + title);
			}
		}

		driver.quit();
	}

	@Test
	@Disabled
	public void verifyProductNamesOnPage(){
		WebDriver driver = new ChromeDriver();
		driver.get("https://java22.netlify.app/");

		List<WebElement> products = driver.findElements(By.className("card-title"));

		for (WebElement product : products) {
			System.out.println(product.getText());
		}
		driver.quit();
	}

	@Test
	@Disabled//lyckas
	public void testAllProductInCategories() {
		// Skapa en instans av webbläsaren och vänta på att sidan laddas
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(25));
		driver.get("https://java22.netlify.app/");

		// Hämta referenser till de klickbara produktkategorierna efter att de har laddats in i DOM
		WebElement categoryElectronics = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[2]/a")));
		WebElement categoryJewelry = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[3]/a")));
		WebElement categoryMens = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[4]/a")));
		WebElement categoryWomens = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[5]/a")));

		// Skapa en lista med förväntade antal produkter för varje produktkategori
		int[] expectedTotal = {6, 4, 4, 6};

		// Loopa igenom varje produktkategori och testa att rätt antal produkter visas
		for (int i = 0; i < expectedTotal.length; i++) {
			// Klicka på produktkategorin
			switch(i) {
				case 0:
					categoryElectronics.click();
					break;
				case 1:
					categoryJewelry.click();
					break;
				case 2:
					categoryMens.click();
					break;
				case 3:
					categoryWomens.click();
					break;
				default:
					break;
			}
			// Vänta på att produkterna laddas och räkna antalet produkter
			List<WebElement> productList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("productItem")));
			int actualTotal = productList.size();

			// Jämför det faktiska antalet produkter med det förväntade antalet produkter
			if (actualTotal == expectedTotal[i]) {
				System.out.println("Produktkategori " + (i+1) + " testad: Rätt antal produkter visas");
			} else {
				System.out.println("Produktkategori " + (i+1) + " testad: Fel antal produkter visas");
			}
		}
		// Stäng webbläsaren
		driver.quit();
	}

	/*
	@Test
	public void testCategories3() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://java22.netlify.app/");

		List<WebElement> categoryElements1 = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[2]/a"));
		List<WebElement> categoryElements2 = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/a"));
		List<WebElement> categoryElements3 = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[4]/a"));
		List<WebElement> categoryElements4 = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[5]/a"));

		List<String> actualCategoryNames = new ArrayList<String>();
		for (WebElement categoryElement : categoryElements1) {
			actualCategoryNames.add(categoryElement.getText());
		}
		for (WebElement categoryElement : categoryElements2) {
			actualCategoryNames.add(categoryElement.getText());
		}
		for (WebElement categoryElement : categoryElements3) {
			actualCategoryNames.add(categoryElement.getText());
		}
		for (WebElement categoryElement : categoryElements4) {
			actualCategoryNames.add(categoryElement.getText());
		}

		List<String> expectedCategoryNames = Arrays.asList("electronics", "jewelery", "men's clothing", "women's clothing");
		assertEquals(expectedCategoryNames, actualCategoryNames);
		System.out.println("Test categories are: " + actualCategoryNames);
		driver.quit();
	}

}*/

	// Kontrollera att priset blir rätt på minst 3 produkter
	/*for (int i = 0; i < 3; i++) {*/
		/*WebElement product = products.get(0);
		double expectedPrice = 22.3; // Byt ut detta med det förväntade priset
		double actualPrice = Double.parseDouble(
				product.findElement(By.xpath(
						"//*[@id=\"productsContainer\"]/div/div[1]/div/div/p")).getText());

				if (expectedPrice == actualPrice) {
			System.out.println("Priset stämmer på produkt ");
		} else {
			System.out.println("Priset stämmer inte på produkt ");
		}*/


	// Hitta elementen som innehåller prisinformationen
		/*List<WebElement> priceElements = driver.findElements(By.cssSelector(".price"));

		// Kontrollera att det finns minst 3 produkter
		assertTrue(priceElements.size() >= 3);

		// Jämför priserna med förväntade värden för minst 3 produkter
		assertEquals(109.95, priceElements.get(0).getText());
		assertEquals(22.3, priceElements.get(1).getText());
		assertEquals(55.9, priceElements.get(2).getText());

		// Stäng webbläsaren
		driver.quit();*/

	/*// Kontrollera att priset blir rätt på minst 3 produkter
       for (int i = 0; i < 3; i++) {
		WebElement product = products.get(i);
		double expectedPrice = 10.99; // Byt ut detta med det förväntade priset
		double actualPrice = Double.parseDouble(product.findElement(By.className("price")).getText());
		double actualPrice = Double.parseDouble(product.findElement(
		By.xpath(".//p[contains(text(),'Pris')]/span")).getText());
		if (expectedPrice == actualPrice) {
			System.out.println("Priset stämmer på produkt " + (i+1));
		} else {
			System.out.println("Priset stämmer inte på produkt " + (i+1));
		}
	}*/
	@Test
	@Disabled//java.lang.IllegalStateException
	public void checkImagesOnProducts() {
		// Set up the driver
		//System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\bin\\msedgedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Navigate to the web page to be tested
		driver.get("https://java22.netlify.app/");

		// Find the products
		List<WebElement> products = driver.findElements(By.className("productItem"));

		// Check the images on at least 3 products
		int count = 0;
		for (WebElement product : products) {
			// Find the image element and get its src attribute
			WebElement image = product.findElement(By.tagName("img"));
			String src = image.getAttribute("src");

			// Check that the image is displayed
			assertTrue(image.isDisplayed());

			// Check that the src attribute is not empty
			assertFalse(src.isEmpty());

			// Increment the count
			count++;
			if (count >= 3) {
				break;
			}
		}
		// Quit the driver
		driver.quit();
	}
}
