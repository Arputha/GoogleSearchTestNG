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
  String rphone;

  @BeforeClass
  public void beforeClass() {
	  //Verify the driver paths in Browser.java before execution
	  gsu = new GoogleSignUp("Chrome", "https://accounts.google.com/signup");
	  driver=gsu.getWD();

  }
  
  @Test 
  public void verifySignUpPage() {
	  rphone="4254993519";
	  gsu.fillSignUpPage ("ken", "adams", "kenadams.qa101.test1",  
				"p@ssword123", 12,01,1992, "male",
				"mano.snow@gmail.com", rphone);
	  WebElement element=driver.findElement(By.id("tos-scroll-button"));
	  if(element.isDisplayed()) {
		  assertTrue(true);
	  }
	  else {
		  assertTrue(false);
	  }
  }
  
  @Test (dependsOnMethods = {"verifySignUpPage"})
  public void verifyTOS() {
		boolean loop=true;
		int i=0;
		WebElement element = null;
		while(loop) {
			element=driver.findElement(By.id("tos-scroll-button"));
			if(element.isDisplayed() || element.isEnabled()) {
//				System.out.print("Clicking on scroll button. \t");
				try {
					element.click();
				}
				catch (Exception e) {
					System.out.println("scroll button unavailable");
				}
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
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
		if(!loop && element.isDisplayed()) {
			assertTrue(true);
		}
		
  }
  
  @Test (dependsOnMethods = {"verifyTOS"})
  public void verifyCancelTOS() {
	  WebElement element=null;
	  element=driver.findElement(By.id("cancelbutton"));
	  element.click();
	  if(!element.isDisplayed()) {
		  assertTrue(true);
	  }
  }
  @Test (dependsOnMethods = {"verifyCancelTOS"})
  public void verifySignUpSubmit() {
	WebElement element=driver.findElement(By.name("submitbutton"));
	element.submit();
	element=driver.findElement(By.id("cancelbutton"));
	if(element.isDisplayed()) {
		  assertTrue(true);
	  }
  }
  
  @Test (dependsOnMethods = {"verifySignUpSubmit"})
  public void verifyAgreeTOS() {
	  WebElement element=null;
	  element=driver.findElement(By.id("iagreebutton"));
	  element.click();
	  element=driver.findElement(By.id("signupidvinput"));
	  if(element.isDisplayed()) {
		  assertTrue(true);
	  }
  }
  
  @Test (dependsOnMethods = {"verifyAgreeTOS"})
  public void verifyConfirmationPhone() {
	  WebElement element = driver.findElement(By.id("signupidvinput"));
		String str;
		
		str=element.getAttribute("value");
		if (rphone.equals(str.replaceAll("[^\\d+xX]", ""))) {
			assertTrue(true);
		}
  }
  

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
