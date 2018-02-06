

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class GoogleSearch_Test {
  WebDriver driver;
  String searchKeyWord;

  @BeforeClass
  public void beforeClass() {
	  //String pattern for search
	  searchKeyWord="Cheese!";
	  // Verify the driver path in Browser.java
	  GoogleSearch gs=new GoogleSearch("Chrome", searchKeyWord);
	  driver=gs.getWD();
	  
  }
  
  @Test
  public void loadTimeCheck() {
	  WebElement element = driver.findElement(By.id("resultStats"));
	  String str=element.getText();
//	  System.out.println(str);
	  str=str.split("\\(")[1].replace(")","").split(" ")[0];
	  if (Float.parseFloat(str)<1.00) {
		 assertTrue(true);
	  }
	  else {
		  assertTrue(false);
	  }
  }
  @Test
  public void verifyTitle() {
	  String str = driver.getTitle();
		if (str.contains(searchKeyWord)) {
			//System.out.println("Search keyword is available in the title");
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
  }
  @Test
  public void verifySignin() {
	  WebElement element=driver.findElement(By.id("gb_70"));
	  String expectedURL="https://accounts.google.com/ServiceLogin";
	  String url = element.getAttribute("href");
	  String attrVal = element.getText();
//	  System.out.println(url+":"+attrVal);
	  if (url.contains(expectedURL) && attrVal.equals("Sign In")) {
		  assertTrue(true);
	  }
	  
  }
  
  @Test
  public void verifySearchKeyword() {
	  WebElement element=driver.findElement(By.id("lst-ib"));
	  String strValue=element.getAttribute("value");
//	  System.out.println(strValue);
	  if (strValue.equals(searchKeyWord)) {
		  assertTrue(true);
	  }
	  else {
		  assertTrue(false);
	  }
  }

  @Test
  public void verifyGoogleAppsLink() {
	  WebElement element=driver.findElement(By.xpath("//*[@id=\"gbwa\"]/div[1]/a"));
	  String url = element.getAttribute("href");
	  String title = element.getAttribute("title");
	  String expectedURL="https://www.google.com/intl/en/options/";
	  System.out.println(url+":"+title);
	  if (url.contains(expectedURL) && title.equals("Google apps")) {
		  assertTrue(true);
	  }
	  else {
		  assertTrue(false);
	  }
	  
  }
  

  @AfterClass
  public void afterClass() {
	driver.quit();  
  }

}
