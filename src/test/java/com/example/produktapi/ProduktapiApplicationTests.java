package com.example.produktapi;

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

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProduktapiApplicationTests {

	@Test   //java.lang.IllegalStateException
	public void webShopTitle() {
		// Skapa en instans av Chrome-webbläsaren med Selenium
		//System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\bin\\msedgedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Gå till webbplatsen som ska testas
		driver.get("https://java22.netlify.app/");

		// Kontrollera att webbplatsens titel stämmer
		String expectedTitle = "Webbutik11";
		String actualTitle = driver.getTitle();
		if (expectedTitle.equals(actualTitle)) {
			System.out.println("Webbplatsens titel stämmer");
		} else {
			System.out.println("Webbplatsens titel stämmer inte");
		}

		// Kolla att det totala antalet produkter stämmer
		List<WebElement> products = driver.findElements(By.className("productItem"));
		int expectedProductCount = 20;
		int actualProductCount = products.size();
		if (expectedProductCount == actualProductCount) {
			System.out.println("Antalet produkter stämmer");
		} else {
			System.out.println("Antalet produkter stämmer inte");
		}
		//driver.quit();

	}

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
	}
	@Test   //java.lang.IllegalStateException
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

	@Test     //org.openqa.selenium.SessionNotCreatedException
	public void checkIfTheBackPackPriceIsRightTimeoutException() {
		//System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\bin\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();

		try {
			driver.get("https://java22.netlify.app/");

			WebElement waiter = new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("/html/body/div/div/div[6]/div/div/div/div[1]/div/div/p")));
			String text = waiter.getText();
			String digits = text.replaceAll("[^0-9.]", "");

			assertEquals("109.95", digits);
			System.out.println("TEST is text: " + text);
			System.out.println("Price is: " + digits);
		} catch (TimeoutException e) {
			System.out.println("TimeoutException: Element not found within 10 seconds.");
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}
/*
	@Test
	public void checkIfTheBackPackPriceIsRight1() {
		//System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		WebDriver driver = new EdgeDriver();

		driver.get("https://java22.netlify.app/");

		WebElement waiter = new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id=\"productsContainer\"]/div/div[1]/div/div/p")));
		String text = waiter.getText();
		String digits = text.replaceAll("[^0-9.]", "");

		assertEquals("109.95", digits);
		System.out.println("TEST is text: " + text);
		System.out.println("Price is: " + digits);
		driver.quit();
	}*/

	/*
		@Test
		public void testCategories() {
			WebDriver driver = new ChromeDriver();
			driver.get("https://java22.netlify.app/");

			List<WebElement> categories = driver.findElements(By.xpath("//div[@class='list-group']/a"));

			assertEquals(4, categories.size()); // make sure we found all categories

			// check each category name
			assertEquals("electronics", categories.get(0).getText().toLowerCase());
			assertEquals("jewelery", categories.get(1).getText().toLowerCase());
			assertEquals("men's clothing", categories.get(2).getText().toLowerCase());
			assertEquals("women's clothing", categories.get(3).getText().toLowerCase());

			driver.quit();
		}*/
	/*
	@Test
	public void testCategories2() {
		//System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://java22.netlify.app/");

		List<WebElement> categoryElements =
				driver.findElements(By.className("menuLink"));

		List<String> actualCategoryNames = new ArrayList<String>();
		for (WebElement categoryElement : categoryElements) {
			actualCategoryNames.add(categoryElement.getText());
		}

		List<String> expectedCategoryNames = Arrays.asList("electronics", "jewelery", "men's clothing", "women's clothing");

		assertEquals(expectedCategoryNames, actualCategoryNames);
		System.out.println(actualCategoryNames);
		driver.quit();
	}*/
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
}