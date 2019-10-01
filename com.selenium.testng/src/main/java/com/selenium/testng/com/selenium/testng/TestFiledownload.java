package com.selenium.testng.com.selenium.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestFiledownload {
	
	public static WebDriver driver;
	static String baseurl="http://demo.guru99.com/test/yahoo.html";
	
@Test
public void initialization() throws IOException
{
	System.setProperty("webdriver.chrome.driver", "E:\\Chromedriver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get(baseurl);
	driver.manage().window().maximize();
	WebElement downloadButton = driver.findElement(By.id("messenger-download"));
	        String sourceLocation = downloadButton.getAttribute("href");
	        String wget_command = "cmd /c E:\\Wget\\wget.exe -P E: --no-check-certificate " + sourceLocation;

	        try {
	        Process exec = Runtime.getRuntime().exec(wget_command);
	        int exitVal = exec.waitFor();
	        System.out.println("Exit value: " + exitVal);
	        } 
	        catch (InterruptedException ex) {
	        System.out.println(ex.toString());
	        }
	        driver.close();
	        }
	      
	
}
