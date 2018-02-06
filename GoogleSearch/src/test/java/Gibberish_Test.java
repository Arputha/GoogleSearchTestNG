
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class Gibberish_Test {

  WebDriver driver;
  String searchKeyWord;
  @Test
  public void checkGibberish() {
		String str = driver.getTitle();
		System.out.println(str);
		WebElement element1 = driver.findElement(By.className("mnr-c"));
		str=element1.getText();
		if (str.contains("did not match any documents")) {
			assertTrue(true);
//			System.out.println("gibberish search navigates to error page");
		}
		else {
			assertTrue(false);
		}
  }
  @BeforeClass
  public void beforeClass() {
	  searchKeyWord="zdafadsfasoiwefsdlasdfasldk";
	  GoogleSearch gs=new GoogleSearch("Chrome", searchKeyWord);
	  driver=gs.getWD();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
