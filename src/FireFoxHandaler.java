import org.openqa.selenium.chrome.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FireFoxHandaler {
	WebDriver driver;

	FireFoxHandaler(){
	}
	
	public boolean loadPage(String url) {
		
		System.setProperty("webdriver.gecko.driver",
				"Geckodriver\\v0.26.0-win64\\geckodriver.exe");

		driver = new FirefoxDriver();
		//driver = new FirefoxDriver();
		driver.get(url);
		
		return true;
	}
	
	public boolean openNextPage() {
		System.out.println(" <- openNextPage clicked");
	
		String newPage = "//div[@class=\"css-1e18wkr\"]/div[@class=\"css-1k2c5c4\"]/a[child::span[contains(text(), \"Next page\")]]";
		
		if(isElementPresent(By.xpath(newPage))){
			driver.findElement(By.xpath(newPage)).click();
			By by = By.xpath("//div[@data-testid=\"search-page__results\"]/div/ul[@data-testid=\"search-results__card-container\"]/li");
			witUntillVisible(by);
			return true;
		}else {
			return false;
		}
	}
		
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public int currentPageNumber() {
		
		try {
			By nextPageItem = By.xpath("//div[@class=\"css-1e18wkr\"]/div[@class=\"css-1k2c5c4\"]/div[@data-testid=\"pagination-text\"]");		
			WebElement element = driver.findElement(nextPageItem);
			System.out.println(element.getText());
			String data = element.getText().replace("Page", "");
			String pagetext = data.substring(0, data.indexOf("of")).trim();
			System.out.println(pagetext);
			return Integer.parseInt(pagetext);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}
	
	public LinkedList<String> takeCompanyList(){
		LinkedList<String> company = new LinkedList<>();
		
		By businessBy = By.xpath("//div[@class=\"css-1u1vh3r\"]/div[@class=\"css-13vwpzu\"]/a[1][@class=\"css-mf6d8o touchable\"]");
		
		List<WebElement> elements = driver.findElements(businessBy);
		Iterator<WebElement> it = elements.iterator();
		
		while(it.hasNext()) {
			String url = "";
			try {
				url = it.next().getAttribute("href");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			company.add(url);
			System.out.println(url);
		}
		
		return company;
	}
	
	public Info openpage(String url) {
		try {
			driver.get(url);
			return getData(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Info();
		}
	}
	
	private String mainTabHandaler = null; 
	private String subWindowHandler = null; 
	
	public void createSubTab() {
		mainTabHandaler = driver.getWindowHandle();
		
		//((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
		((JavascriptExecutor) driver).executeScript("window.open();");
	   	  Set<String> handles = driver.getWindowHandles();
		  Iterator<String> iterator = handles.iterator();
		  while (iterator.hasNext()) {
		   subWindowHandler = iterator.next();
		  }
	}
	
	public void switchSubTab() {
		driver.switchTo().window(subWindowHandler);
	}
	public void switchMainTab() {
		driver.switchTo().window(mainTabHandaler);
	}
	
	private Info getData(String url) {
		Info info = new Info();
		info.setBusinessProfileUrl(url);
		
		WebElement webElement ;
		
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"description\"]/div[1]/h1"));
			info.setName(webElement.getText());
			System.out.println("Title: " + webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"description\"]/div[1]/ul/li[1]/div/span"));
			info.setType(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"description\"]/div[1]/ul/li[2]/div/span"));
			info.setArea(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"description\"]/div[1]/ul/li[3]/div/span"));
			info.setSaleContact(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"description\"]/div[2]"));
			info.setDescription(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"static-map-container\"]/div[2]/div[1]/div/picture/img"));
			//https://maps.googleapis.com/maps/api/staticmap?center=-33.71294,150.8415&amp;maptype=roadmap&amp;format=png&amp;
			String latLong = webElement.getAttribute("src");
	
//			System.out.println("latLong " + data);
//			String temp = data.replaceFirst("(?<=https)(.+?)(?=\\?center=)", "");
//			System.out.println("temp " + temp);
//			String latLong = temp.substring(0, temp.indexOf("&maptype"));
			//String latLong = data.substring(data.indexOf("=")+1, data.indexOf("&"));
			info.setLatlong(latLong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Property ID\")]]"));
			info.setPropertyID(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Floor Area\")]]"));
			info.setFloorArea(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Land Area\")]]"));
			info.setLandArea(webElement.getText());   
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Energy\")]]"));
			info.setEnergy(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Parking\")]]"));
			info.setParking(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Building Type\")]]"));
			info.setBuildingType(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Annual Return\")]]"));
			info.setAnnualReturn(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Availability\")]]"));
			info.setAvailability(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Category\")]]"));
			info.setCategory(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/section[2]/section[2]/table/tbody/tr/td[preceding-sibling::th[contains(text(), \"Last updated\")]]"));
			info.setLastUpdated(webElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		LinkedList<String> imgs = new LinkedList<>();
		try {
			List<WebElement>  eles = driver.findElements(By.xpath("//*[@id=\"gallery\"]/section/div[1]/a"));
			
			for(int i = 1; i <= eles.size(); i++) {
				webElement = driver.findElement(By.xpath("//*[@id=\"gallery\"]/section/div[1]/a["+i+"]/div/picture/img"));
				                                          
				imgs.add(webElement.getAttribute("src"));
			}
			info.setImageUrl(imgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LinkedList<Agent> agents = new LinkedList<>();
		try {
			List<WebElement>  elsT1 = driver.findElements(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div[2]/div/div/div"));
			List<WebElement>  elsT2 = driver.findElements(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div/div/div/div"));
			Agent agent;
			if(elsT1.size() > 0) {
				for(int i = 1; i <= elsT1.size(); i++) {
					agent = new Agent();
					webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div[2]/div/div/div["+i+"]/div/div[2]/div[1]/div[1]/a"));
					agent.setAgentName(webElement.getText());
					agent.setProfileUrl(webElement.getAttribute("href"));
					driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div[2]/div/div/div["+i+"]/div/div[2]/div[2]/a")).click();
					webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div[2]/div/div/div["+i+"]/div/div[2]/div[2]/a/div/span"));
					agent.setAgentPhone(webElement.getText());
					agents.add(agent);
				}
			}else if(elsT2.size() > 1) {
				for(int i = 1; i <= elsT2.size(); i++) {
					agent = new Agent();
					webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div/div/div/div["+i+"]/div/div[2]/div[1]/div[1]/a"));
					agent.setAgentName(webElement.getText());
					agent.setProfileUrl(webElement.getAttribute("href"));
					driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div/div/div/div["+i+"]/div/div[2]/div[2]/a")).click();
					webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div/div/div/div["+i+"]/div/div[2]/div[2]/a/div/span"));
					agent.setAgentPhone(webElement.getText());
					agents.add(agent);
				}
			}else if(elsT2.size() > 0){
				agent = new Agent();
				webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div/div/div/div/div/div[2]/div[1]/div[1]/a"));
				agent.setAgentName(webElement.getText());
				agent.setProfileUrl(webElement.getAttribute("href"));
				driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div/div/div/div/div/div[2]/div[2]/a")).click();
				webElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/section/div/div/div/div/div/div/div/div/div/div[2]/div[2]/a/div/span"));
				agent.setAgentPhone(webElement.getText());
				agents.add(agent);
			}else {
				agent = new Agent();
				agents.add(agent);
			}
			info.setAgents(agents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return info;
	}
	

	public String pageUrl() {
		return driver.getCurrentUrl();
	}
	public void refreshDriver(String url) {
		driver.quit();
		loadPage(url);
	}
	
	private void witUntillVisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
// ---------------------------------------------------------------------------------------------------//	

	public void findAndClick(String selector) {
		By by = By.cssSelector(selector);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		driver.findElement(by).click();
	}

	public void fullPageScroll() {
		// https://stackoverflow.com/questions/42982950/how-to-scroll-down-the-page-till-bottomend-page-in-the-selenium-webdriver
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("scroll(0, 250);");
			Thread.sleep(1000);
			jse.executeScript("scroll(0, 550);");
			Thread.sleep(1000);
			jse.executeScript("scroll(0, 750);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 4550);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 6000);");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if I direct go to bottom of the page page full content don't load
		// jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	// running system installed firefox [Testing date : 10 Mar 2018]
	// requires : selenium-server-standalone-3.10.0.jar,
	// client-combined-3.10.0.jar & geckodriver.exe

	protected void runfirefoxDefaultProfile() {

		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("default");

		myprofile.setPreference("network.proxy.type", 0);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(FirefoxDriver.PROFILE, myprofile);
		
		System.setProperty("webdriver.gecko.driver",
				"Geckodriver\\v0.26.0-win64\\geckodriver.exe");

		driver = new FirefoxDriver(capabilities);
		//driver = new FirefoxDriver();
		driver.get("https://www.yellowpages.com");

	}

	private static void separatingCityStateZip() {
		String mydata = "Los Angeles, CA 90026";
		Pattern pattern = Pattern.compile("([^,]+), ([A-Z]{2}) (\\d{5})");
		Matcher matcher = pattern.matcher(mydata);
		if (matcher.find()) {
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
		}
		
		//String mydata1 = "144 Kings Highway, S.W. Dover, DE 19901";
		//String mydata1 = "2299 Lewes-Georgetown Hwy, Georgetown, DE 19947";
		//String mydata1 = "580 North Dupont Highway, Dover, DE 19901";
		String mydata1 = "PO Box 778, Dover, DE 19901";
		Pattern pattern1 = Pattern.compile("([^,]+), ([^,]+), ([A-Z]{2}) (\\d{5})");
		Matcher matcher1 = pattern1.matcher(mydata1);
		if (matcher1.find()) {
			System.out.println(matcher1.group(1));
			System.out.println(matcher1.group(2));
			System.out.println(matcher1.group(3));
			System.out.println(matcher1.group(4));
		}
		
	
	}
	
}
