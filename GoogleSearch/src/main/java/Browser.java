import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser  {
	WebDriver driver;
	
	public void initChrome() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Selenium\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver= new ChromeDriver();
	}
	public void initFirefox() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Selenium\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		driver= new FirefoxDriver();
	}
	Browser(String brws, String site) {
		if(brws.equals("Chrome")) {
			initChrome();
		}
		else if (brws.equals("Firefox")) {
			initFirefox();
		}
		initBrowser(site);
	}
	public void loadPage(String webPage) {
		driver.get(webPage);
	}
	public WebDriver getWD() {
		return driver;
	}
	void initBrowser(String site) {
		loadPage(site);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
