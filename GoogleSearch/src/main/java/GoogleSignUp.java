import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSignUp {
	WebDriver driver;
	GoogleSignUp(String browser, String site) {
		Browser brws;
		brws=new Browser(browser, site);
		driver=brws.getWD();
	}
	
	WebDriver getWD() {
		return driver;
	}
	
	String birthMonthStr(int bmonth) {
		if (bmonth < 10) {
			return String.valueOf(bmonth);
		}
		else if (bmonth == 10) {
			return "a";
		}
		else if (bmonth == 11) {
			return "b";
		}
		else {
			return "c";
		}
	}
	
	String getGenderCode(String gender) {
		if (gender.equals("male")) {
			return "f";
		}
		else if (gender.equals("female")) {
			return "e";
		}
		else {
			return "g";
		}
	}
	
	void fillSignUpPage(String fname, String lname,
						String gadress, String passswd,
						int birthmonth, int birthyear, int birthday,
						String gender, String remail, String rphone
			) {
		WebElement element = driver.findElement(By.name("FirstName"));
		element.sendKeys(fname);
		element = driver.findElement(By.name("LastName"));
		element.sendKeys(lname);
		element = driver.findElement(By.name("GmailAddress"));
		element.sendKeys(gadress);
		element = driver.findElement(By.name("Passwd"));
		element.sendKeys(passswd);
		element = driver.findElement(By.name("PasswdAgain"));
		element.sendKeys(passswd);
		
		driver.findElement(By.xpath(".//*[@id='BirthMonth']/div")).click();
		driver.findElement(By.xpath(".//*[@id=':"+birthMonthStr(birthmonth)+"']/div")).click(); //1-9,a,b,c
		
		element = driver.findElement(By.name("BirthDay"));
		element.sendKeys("15");
		element = driver.findElement(By.name("BirthYear"));
		element.sendKeys("1998");
		
		driver.findElement(By.xpath(".//*[@id='Gender']/div")).click();
		driver.findElement(By.xpath(".//*[@id=':"+getGenderCode(gender)+"']/div")).click(); //e->female;f->male;g &h for other options
		
		element = driver.findElement(By.name("RecoveryPhoneNumber"));
		element.sendKeys(rphone);
		
		element = driver.findElement(By.name("RecoveryEmailAddress"));
		element.sendKeys(remail);
		element=driver.findElement(By.name("submitbutton"));
		element.submit();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	void scrollTOS() {
		boolean loop=true;
		int i=0;
		WebElement element = null;
		while(loop) {
			element=driver.findElement(By.id("tos-scroll-button"));
			if(element.isDisplayed()) {
//				System.out.print("Clicking on scroll button. \t");
				element.click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
		element.click();
	}
	
	boolean verifyPhoneNumber(String rphone) {
		WebElement element = driver.findElement(By.id("signupidvinput"));
		String str;
		
		str=element.getAttribute("value");
		if (rphone.equals(str.replaceAll("[^\\d+xX]", ""))) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GoogleSignUp gsu = new GoogleSignUp("Chrome", "https://accounts.google.com/signup");
		gsu.fillSignUpPage ("ken", "adams", "kenadams.qa101.test1",  
				"p@ssword123", 12,01,1992, "male",
				"mano.snow@gmail.com", "4254993519");
		
		System.out.println("Navigating to the popup");
		
		gsu.scrollTOS();
		
		if (gsu.verifyPhoneNumber("4254993519")) {
			System.out.println("Phone number in signup page is in sync with confirmation page");
		}
	}
	


}
