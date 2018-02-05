import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchGibberish {
	static WebDriver driver;
	static boolean checkGibberish() {
		String str = driver.getTitle();
		System.out.println(str);
		WebElement element1 = driver.findElement(By.className("mnr-c"));
		str=element1.getText();
		if (str.contains("did not match any documents")) {
			return true;
//			System.out.println("gibberish search navigates to error page");
		}
		return false;
	}	
	GoogleSearchGibberish(String browser, String searchKeyWord){
		String strToSearch="zdafadsfasoiwefsdlasdfasldk";
		GoogleSearch gs=new GoogleSearch("Chrome", strToSearch);
		driver=gs.getWD();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strToSearch="zdafadsfasoiwefsdlasdfasldk";
		GoogleSearchGibberish gs=new GoogleSearchGibberish("Chrome", strToSearch);
		if (checkGibberish()) {
			System.out.println("gibberish search navigates to error page");
		}
	}

	
}
