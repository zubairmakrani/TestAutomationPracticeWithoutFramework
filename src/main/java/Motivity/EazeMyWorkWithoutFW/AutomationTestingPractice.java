package Motivity.EazeMyWorkWithoutFW;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AutomationTestingPractice {

	public static void main(String args[]) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com");

		String parent_windid = driver.getWindowHandle();

		// getpagetitle
		String title = driver.getTitle();

		System.out.println(title);

		WebElement h1 = driver.findElement(By.xpath("//h1[contains(text(),'Automation Testing Practice')]"));
		String Heading = h1.getAccessibleName();
		System.out.println(Heading);

		if (Heading.trim().equalsIgnoreCase("Automation Testing Practice")) {
			System.out.println("We are on the landing page");

		} else {
			System.out.println("Failed to load the landing page");
		}

		WebElement name = driver.findElement(By.xpath("//input[@id='name']"));
		boolean displayname = name.isEnabled();
		if (displayname == true) {
			name.clear();
			name.sendKeys("zubair makrani");
		}

		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		boolean displayemail = email.isEnabled();
		if (displayemail == true) {
			email.clear();
			email.sendKeys("zubair.makrani@MLI.com");
		}

		WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));
		boolean displaypnone = phone.isEnabled();
		if (displaypnone == true) {
			phone.clear();
			phone.sendKeys("1233211230");
		}

		WebElement address = driver.findElement(By.xpath("//textarea[@class='form-control']"));
		boolean displayaddress = address.isEnabled();
		if (displayaddress == true) {
			address.clear();
			address.sendKeys("Abc area Near Xyz, west central Dallas, 77077");
		}

		WebElement male = driver.findElement(By.xpath("//input[@id='male']"));

		boolean displaymale = male.isSelected();
		if (displaymale == false) {
			male.click();
		}
		System.out.println("Male radio button is selected: " + displaymale);

		WebElement days = driver.findElement(By.xpath("//label[text()='Days:']/.."));

		List<WebElement> daysss = days
				.findElements(By.xpath("//div[@class='form-check form-check-inline']//input[@type='checkbox']"));
		for(WebElement day:daysss) {
			String dayName=day.getAttribute("id");
				List<String>dayys=Arrays.asList("Tuesday","Wednesday","Saturday");
				for(String dayysss:dayys) {
					if(dayName.equalsIgnoreCase(dayysss)) {
						day.click();
						System.out.println(dayName+" is selected as per the matching"+dayysss);
						
					}
				}
			
			
		}
		
		
		
		
		
		/*
		 * for (WebElement child : daysss) { String day = child.getText();
		 * System.out.println(day); boolean selected = child.isSelected(); if (selected
		 * == true) { System.out.println(day + " is selected already"); } else {
		 * System.out.println(day + " is Not selected"); child.click(); }
		 * 
		 * }
		 */

		// sroll to listbox

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,500)");

		// ListBox
		Thread.sleep(2000);
		List<WebElement> options = driver.findElements(By.xpath("//select[@id='country']/option"));
		int count = options.size();
		System.out.println(count);
		for (WebElement option : options) {
			String country = option.getText();
			System.out.println(country);
			if (country.equals("India")) {

				option.click();
				System.out.println(country + " is selected successfully");
			} else {
				System.out.println("India is not found yet");
			}

		}

		js.executeScript("scrollBy(0,-500)");

		WebElement search = driver.findElement(By.xpath("//input[@id='Wikipedia1_wikipedia-search-input']"));
		search.clear();
		search.sendKeys("Selenium");
		WebElement search_btn = driver.findElement(By.xpath("//input[@class='wikipedia-search-button']"));
		boolean isClickable = search_btn.isEnabled();
		Assert.assertTrue(isClickable);
		search_btn.click();
		List<WebElement> res = driver.findElements(By
				.xpath("//div[@id='Wikipedia1_wikipedia-search-results']//div[@id='wikipedia-search-result-link']/a"));
		int results_count = res.size();
		System.out.println(results_count + " are the search results displaying for the searching Selenium");

		for (WebElement result : res) {

			String display = result.getText();
			// System.out.println(display);
			String href = result.getAttribute("href");
			// System.out.println(href);
			System.out.println("Display name: " + display + " and the URL belongs to it: " + href);
			result.click();
			String current_page = driver.getTitle();
			System.out.println(current_page);

		}
		Set<String> windowids = driver.getWindowHandles();
		
		
		  for (String windId : windowids) { 
		  driver.switchTo().window(windId);
		  Thread.sleep(2000); 
		  String title_win = driver.getTitle();
		  System.out.println(title_win);
		  
		  if (!parent_windid.equalsIgnoreCase(windId)) {
		  
		  driver.close();
		  
		  } 
		  else { 
		  String homepage = driver.getCurrentUrl();
		  System.out.println(homepage); } 
		  }
		 
		
		/*Iterator<String> iter=windowids.iterator();
		while(iter.hasNext()) {
			String childId=iter.next();
			if(!parent_windid.equalsIgnoreCase(childId)) {
				driver.switchTo().window(childId);
				System.out.println(driver.getTitle());
				driver.close();
			}
			else {
				System.out.println(driver.getCurrentUrl());
			}
			
			}
			*/
		driver.switchTo().window(parent_windid);
		Thread.sleep(2000);
		System.out.println(driver.getCurrentUrl()+"ye hai parent");
		WebElement alert_btn=driver.findElement(By.xpath("//button[text()='Alert']"));
		boolean iSclickable=alert_btn.isDisplayed();
		Assert.assertTrue(iSclickable);
		alert_btn.click();
		
		Alert alert=driver.switchTo().alert();
		String alert_text=alert.getText();
		System.out.println(alert_text);
		
		alert.accept();
		
		js.executeScript("window.scrollBy(0,500)");
		
		WebElement drag=driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement drop=driver.findElement(By.xpath("//div[@id='droppable']"));
		
		Actions act=new Actions(driver);
		Thread.sleep(2000);
		act.clickAndHold(drag).moveToElement(drop).build().perform();
		
		WebElement slide=driver.findElement(By.xpath("//div[@id='slider']/span"));
		act.dragAndDropBy(slide, 300, 0).build().perform();
		
		js.executeScript("scrollBy(0,500)");
		
		//WebElement frame=driver.findElement(By.xpath("//iframe[@id='frame-one796456169']"));
		driver.switchTo().frame("frame-one796456169");
		WebElement namefield=driver.findElement(By.xpath("//input[@id='RESULT_TextField-0']"));
		
		namefield.clear();
		namefield.sendKeys("Zubair");
		
		WebElement dropdown2=driver.findElement(By.xpath("//select[@id='RESULT_RadioButton-3']"));
		
		Select select=new Select(dropdown2);
		
		WebElement first=select.getFirstSelectedOption();
		
		if(first!=null) {
			System.out.println(first.getText().trim()+" Default option selected");
		}
		else {
			System.out.println("No default option selected");
		}
		
		select.selectByVisibleText("Manager");
		System.out.println(select.getFirstSelectedOption().getText());//manager
		select.selectByIndex(1);
		System.out.println(select.getFirstSelectedOption().getText());//qa 
		select.selectByValue("Radio-2");
		System.out.println(select.getFirstSelectedOption().getText());//dev
		
		

		/*
		 * System.out.println(windowids);
		 * 
		 * int windowsCount = windowids.size(); System.out.println(windowsCount);// 6
		 * 
		 * Iterator<String> iter=windowids.iterator(); while(iter.hasNext()) {
		 * 
		 * }
		 */

		/*
		 * List<String> list=new ArrayList<String>(); Iterator<String> iter =
		 * windowids.iterator();
		 * 
		 * while (iter.hasNext()) { list.add(iter.next());
		 * 
		 * driver.switchTo().window(iter.next()); System.out.println(
		 * driver.getTitle()); driver.close();
		 * 
		 * }
		 * 
		 * for(int i=list.size()-1;i>=0;i--) { driver.switchTo().window(list.get(i));
		 * System.out.println( driver.getTitle()); if
		 * (!parent_windid.equalsIgnoreCase(list.get(i))) { driver.close(); } else {
		 * String homepage = driver.getCurrentUrl(); System.out.println(homepage); } }
		 */
	}

	/*
	 * for (String windId : windowids) { 
	 * driver.switchTo().window(windId);
	 * Thread.sleep(5000); 
	 * String title_win = driver.getTitle();
	 * System.out.println(title_win);
	 * 
	 * if (!parent_windid.equalsIgnoreCase(windId)) {
	 * 
	 * driver.close();
	 * 
	 * } 
	 * else { 
	 * String homepage = driver.getCurrentUrl();
	 * System.out.println(homepage); } 
	 * }
	 */
		
}
