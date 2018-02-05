

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
  
  @Test
  public void loadTimeCheck() {
	  WebElement element = driver.findElement(By.id("resultStats"));
	  String str=element.getText();
	  System.out.println(str);
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
  @BeforeClass
  public void beforeClass() {
	  //String pattern for search
	  searchKeyWord="Cheese!";
	  GoogleSearch gs=new GoogleSearch("Chrome", searchKeyWord);
	  driver=gs.getWD();
	  
  }

  @AfterClass
  public void afterClass() {
	  
  }

}
