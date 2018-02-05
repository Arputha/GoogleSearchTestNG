import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearch {
	WebDriver driver;
	public void searchGoogle(String searchKeyWord) {
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys(searchKeyWord);
		WebElement element1 = driver.findElement(By.name("btnK"));
		element1.submit();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	private void initBrowser(String browser, String site) {
		Browser brws;
		brws=new Browser(browser, site);
		driver=brws.getWD();
	}
	GoogleSearch(String browser, String searchKeyWord) {
		initBrowser(browser, "http://www.google.com");
		searchGoogle(searchKeyWord);
	}
	boolean verifyTitle(String searchKeyWord) {
		String str = driver.getTitle();
		if (str.contains(searchKeyWord)) {
			//System.out.println("Search keyword is available in the title");
			return true;
		}
		return false;
	}
	boolean verifySearchTime() {		
		WebElement element1 = driver.findElement(By.id("resultStats"));
		String str=element1.getText();
		System.out.println(str);
		str=str.split("\\(")[1].replace(")","").split(" ")[0];
		if (Float.parseFloat(str)<1.00) {
//			System.out.println("Search results were returned in less than one second");
			return true;
		}
		return false;
	}
	public WebDriver getWD() {
		return driver;
	}
	
	public static void main(String[] args) {
		String strToSearch="Cheese!";
		GoogleSearch gs=new GoogleSearch("Chrome", strToSearch);
		
		if (gs.verifyTitle(strToSearch)) {
			System.out.println("Search keyword is available in the title");
		}
		
		if (gs.verifySearchTime()) {
			System.out.println("Search results were returned in less than one second");
		}
	}
}
