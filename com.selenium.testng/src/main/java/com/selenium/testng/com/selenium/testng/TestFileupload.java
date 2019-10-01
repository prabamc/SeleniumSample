package com.selenium.testng.com.selenium.testng;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestFileupload {
	
	public static WebDriver driver; 
	static String baseurl="http://demo.guru99.com/test/upload/";
	@Test
	public void initialization() throws IOException
	{
		System.setProperty("webdriver.chrome.driver", "E:\\Chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseurl);
		driver.manage().window().maximize();
		WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));
	    uploadElement.sendKeys("E:\\File Upload Demo.html");
	    driver.findElement(By.id("terms")).click();
	    driver.findElement(By.id("submitbutton")).click();
	    WebElement displayMessage = driver.findElement(By.cssSelector("#res > center"));
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	    String dismess = displayMessage.getText();
	    System.out.println(dismess);
	  
	}
	

}
