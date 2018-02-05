import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class GoogleSignUp_Test {
  GoogleSignUp gsu;
  WebDriver driver;
  @Test (priority=1)
  public void verifySignUpPage() {
	  gsu.fillSignUpPage ("ken", "adams", "kenadams.qa101.test1",  
				"p@ssword123", 12,01,1992, "male",
				"mano.snow@gmail.com", "4254993519");
	  WebElement element=driver.findElement(By.id("tos-scroll-button"));
	  if(element.isDisplayed()) {
		  assertTrue(true);
	  }
	  else {
		  assertTrue(false);
	  }
  }
  
  @Test (priority=2)
  public void verifyTOS() {
		boolean loop=true;
		int i=0;
		WebElement element = null;
		while(loop) {
			element=driver.findElement(By.id("tos-scroll-button"));
			if(element.isDisplayed()) {
//				System.out.print("Clicking on scroll button. \t");
				element.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			element=driver.findElement(By.id("iagreebutton"));
			
			if (element.isDisplayed()) {
				System.out.println("Agree button is finally available : "+i);
				loop=false;
				
			}
/*			else {
				System.out.println("Agree button is not available : "+i);
			}*/
			i++;
		}
		element=driver.findElement(By.id("cancelbutton"));
		if(loop && element.isDisplayed()) {
			assertTrue(true);
		}
		
  }
  
  @Test (priority =4)

  @BeforeClass
  public void beforeClass() {
	  gsu = new GoogleSignUp("Chrome", "https://accounts.google.com/signup");
	  driver=gsu.getWD();

  }

  @AfterClass
  public void afterClass() {
  }

}
