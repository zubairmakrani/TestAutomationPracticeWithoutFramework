package Motivity.EazeMyWorkWithoutFW;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class EaseMyTrip {
	
	public static void main(String args[]) throws IOException {
		
		ChromeOptions copt=new ChromeOptions();
		copt.addArguments("--disable-notifications");
		//copt.addArguments("--disable-popup-blocking");
		
		WebDriver driver=new ChromeDriver(copt);
		
		driver.get("https://www.easemytrip.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		WebElement footer=driver.findElement(By.xpath("//div[@class='rightfotmenu']"));
		
		List<WebElement> links=
				footer.findElements(By.xpath("//div[@class='rightfotmenu']/div[@class='menuft1 ftnew flxwrp']/div/ul/li/a"));
		
		for(WebElement link:links) {
			String textLink=link.getText();
			System.out.println(textLink);
			String url=link.getAttribute("href");
			
			URL urlObj=new URL(url);
			
			HttpURLConnection httpConn=(HttpURLConnection)urlObj.openConnection();
			httpConn.connect();
			
			int resCode=httpConn.getResponseCode();
			if(resCode>=400) {
				System.out.println(url+" - link is broken");
			}
			else {
				System.out.println(url+" - is a valid link");
			}
		}
		
	}

}
