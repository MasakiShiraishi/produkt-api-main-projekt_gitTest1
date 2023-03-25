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
    @Disabled
	public void webShopTitle() {
		// Skapa en instans av Chrome-webbläsaren med Selenium
		WebDriver driver = new EdgeDriver();

		// Gå till webbplatsen som ska testas
		driver.get("https://java22.netlify.app/");

		// Kontrollera att webbplatsens titel stämmer
		String expectedTitle = "Webbutik";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle, "Webbplatsens titel stämmer inte");

		if (expectedTitle.equals(actualTitle)) {
			System.out.println("Webbplatsens titel stämmer");
		} else {
			System.out.println("Webbplatsens titel stämmer inte");
		}

		driver.quit();
	}
		@Test
		@Disabled
		public void totalProdukterTest() {
			WebDriver driver = new ChromeDriver();

			driver.get("https://java22.netlify.app/");

		// Kolla att det totala antalet produkter stämmer
		List<WebElement> products = driver.findElements(By.className("productItem"));
		int expectedProductCount = 20;

		int actualProductCount = products.size();
			assertEquals(expectedProductCount, actualProductCount, "Antalet produkter stämmer inte");

			if (expectedProductCount == actualProductCount) {
			System.out.println("Antalet produkter stämmer");
		} else {
			System.out.println("Antalet produkter stämmer inte");
		}
		driver.quit();
	}

	@Test
	@Disabled
	public void checkIfTheThreeProductsPriceIsRight() {
		//System.setProperty("webdriver.chrom.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://java22.netlify.app/");

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
		System.out.println(text);
		System.out.println("Price is: " + "\n" + price10995 + "\n" + price223 + "\n" + price5599);

		driver.quit();
	}
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
		WebDriver driver = new ChromeDriver();
		driver.get("https://java22.netlify.app/");

		List<WebElement> categoryElements =
				driver.findElements(By.className("menuLink"));

		List<String> actualCategoryNames = new ArrayList<String>();
		for (WebElement categoryElement : categoryElements) {
			actualCategoryNames.add(categoryElement.getText());
			System.out.println(categoryElement.getText());
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

		List<WebElement> categoryElectronics = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[2]/a"));
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
    public void checkAllProductNameAndPrice(){
	WebDriver driver = new ChromeDriver();
	driver.get("https://java22.netlify.app/");
	List<WebElement> productsPrices = new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
			.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.className("card-text")));
	for (WebElement productsPrice : productsPrices) {
		System.out.println(productsPrice.getText());

	}

}
    @Test
	@Disabled
	public void verifySixProductsPrices() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://java22.netlify.app/");

		List<WebElement> products = driver.findElements(By.className("card"));

		for (WebElement product : products) {
			WebElement titleElement = product.findElement(By.className("card-title"));
			String title = titleElement.getText();

			WebElement productsPrice = product.findElement(By.className("card-text"));
			String priceText = productsPrice.getText();
			//System.out.println(priceText+ "\n");
			double price = Double.parseDouble(priceText.replaceAll("[^0-9.]+", ""));
			double price2 = Double.parseDouble(priceText.replaceAll("[^0-9.]+", "").replaceFirst("\\.", ""));
			//System.out.println(price +" "+ "\nThis is price with replaceFirst: " +price2);
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
			System.out.println("Product: " +  product.getText());
		}
		driver.quit();
	}

	@Test
	@Disabled
	public void testAllProductInCategories() {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		driver.get("https://java22.netlify.app/");

		WebElement categoryElectronics = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[2]/a")));
		WebElement categoryJewelry = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[3]/a")));
		WebElement categoryMens = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[4]/a")));
		WebElement categoryWomens = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[5]/a")));

		// Skapa en lista med förväntade antal produkter för varje produktkategori
		int[] expectedTotal = {6, 4, 4, 6};

		// Loopa igenom varje produktkategori och testa att rätt antal produkter visas
		for (int i = 0; i < expectedTotal.length; i++) {

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
		driver.quit();
	}

	@Test
	@Disabled
	public void checkImagesOnProducts() {
		WebDriver driver = new ChromeDriver();

		driver.get("https://java22.netlify.app/");


		List<WebElement> productImages = driver.findElements(By.cssSelector(".card-img-top"));

		// Kontrollera att det finns minst 3 bilder
		int numImages = productImages.size();
		if (numImages >= 3) {
			System.out.println("Det finns minst 3 produktbilder på sidan.");
		} else {
			System.out.println("Det finns färre än 3 produktbilder på sidan.");
		}
		//System.out.println(productImages.size());
         // Stäng webbläsarens instans
		driver.quit();
	}
	@Test
	@Disabled
	public void checkAllImagesPrintedDisplayed() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://java22.netlify.app/");
		List<WebElement> images = driver.findElements(By.tagName("img"));
		for (WebElement image : images) {
			String source = image.getAttribute("src");
			System.out.println("Bildens källa är: " + source);
		}
	}
	/*@Test
	@Disabled
	public void checkImagesOnProducts() {
		WebDriver driver = new ChromeDriver();

		driver.get("https://java22.netlify.app/");

		List<WebElement> products = driver.findElements(By.cssSelector(".productItem card"));

		// Check the images on products
		for (WebElement product : products) {
			WebElement image1 = product.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[1]/div/img"));
			String src1 = image1.getAttribute("src1");
			WebElement image2 = product.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[2]/div/img"));
			String src2 = image2.getAttribute("src2");
			WebElement image3 = product.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[3]/div/img"));
			String src3 = image3.getAttribute("src3");
			System.out.println(src1 + src2 + src3);

			// Check that the src attribute is not empty
			assertFalse(src1.isEmpty(), String.valueOf(src2.isEmpty()));
            assertEquals("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg", src1);
			assertEquals("https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg", src2);
			assertEquals("https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg", src3);

		}
		// Quit the driver
		driver.quit();
	}*/


}
