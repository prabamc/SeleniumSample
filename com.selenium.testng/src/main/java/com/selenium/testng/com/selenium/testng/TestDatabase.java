package com.selenium.testng.com.selenium.testng;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestDatabase {
	public static WebDriver driver;
	static String baseurl="http://demo.guru99.com/test/newtours/";
	HSSFWorkbook workbook;
	HSSFSheet sheet;
    HSSFCell cell;

@BeforeTest
public void initialization()
{
	System.setProperty("webdriver.chrome.driver", "E:\\Chromedriver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get(baseurl);
	driver.manage().window().maximize();
}

@Test
public void login() throws IOException
{
driver.get(baseurl);
driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a")).click();
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
File src=new File("D:\\Myworkspace\\com.selenium\\Testdata.xls");
FileInputStream fis=new FileInputStream(src);
workbook=new HSSFWorkbook(fis);
sheet=workbook.getSheetAt(0);
File screenshotFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
for(int i=1; i<=sheet.getLastRowNum(); i++)
{
	cell = sheet.getRow(i).getCell(0);
	cell.setCellType(Cell.CELL_TYPE_STRING);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
	driver.findElement(By.name("userName")).clear();
	driver.findElement(By.name("userName")).sendKeys(cell.getStringCellValue());
	
	cell = sheet.getRow(i).getCell(1);
	cell.setCellType(Cell.CELL_TYPE_STRING);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys(cell.getStringCellValue());
	driver.findElement(By.name("submit")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	FileUtils.copyFile(screenshotFile,new File("D:\\Myworkspace\\com.selenium\\ScreenShots\\output.png"));
		
	//write into excel file
	FileOutputStream fos=new FileOutputStream(src);
	String message="PASS";
	sheet.getRow(i).createCell(2).setCellValue(message);
	workbook.write(fos);
			
	}
}

@AfterTest
public void close()
{
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	System.out.println("Successfully Verified User Name & Password");
	driver.close();}

}