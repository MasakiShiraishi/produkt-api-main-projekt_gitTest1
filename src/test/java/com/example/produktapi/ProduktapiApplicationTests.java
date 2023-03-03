package com.example.produktapi;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProduktapiApplicationTests {

	@Test
	public void webShopTitle() {
		// Skapa en instans av Chrome-webbläsaren med Selenium
		WebDriver driver = new ChromeDriver();

		// Gå till webbplatsen som ska testas
		driver.get("https://java22.netlify.app/");

		// Kontrollera att webbplatsens titel stämmer
		String expectedTitle = "Webbutikd";
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
		driver.quit();

	}
  /*
	@Test
	public void checkIfTheBackPackPriceIsRight() {
		WebDriver driver = new ChromeDriver();

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
	public void checkIfTheBackPackPriceIsRight1() {
		WebDriver driver = new ChromeDriver();

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
	}
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
	@Test
	public void testCategories2() {
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
	}

	@Test
	public void imageShouldBeVisible() {
		// hämta in de webdrivers du vill använda
		WebDriver driver = new ChromeDriver();
		// Navigera int till den URL du vill testa dör respektive driver
		driver.get("https://java22.netlify.app/");

		WebElement productImage = new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='card-img-top' and contains(@src, 'https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg')]")));
		assertTrue(productImage.isDisplayed(), "Bilden verkar inte läsas in");

		driver.quit();
		//WebElement productImage = driver.findElements(By.xpath("//img[@class='card-img-top' and contains(@src, 'https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg')]"));
	}

}

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
