package com.feisystems.automationtest.libary;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class SeleniumWrapper {

	Logger logger = null;
	private static boolean isStopBrowser = true;
	private Selenium selenium;
	public WebDriver driver;
	/*private String username = null;
	private String password = null;*/
	private HashMap<String, String> map = new HashMap<String, String>();
	private StringBuffer verificationErrors = new StringBuffer();
	private StringBuffer verificationSuccess = new StringBuffer();
	private ArrayList<VerifyResult> verfifys = new ArrayList<VerifyResult>();

	class VerifyResult<K, V> {
		String page;
		String selector;
		String message;
		String codeLine;
		boolean flag;

		public VerifyResult(String selector, String message, boolean flag) {
			super();
			this.page = selenium.getLocation();
			this.selector = selector;
			this.message = message;
			this.flag = flag;
			this.codeLine = getLineInfo();
		}

		public String getPage() {
			return page;
		}

		public void setPage(String page) {
			this.page = page;
		}

		public String getSelector() {
			return selector;
		}

		public void setSelector(String selector) {
			this.selector = selector;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getCodeLine() {
			return codeLine;
		}

		public void setCodeLine(String codeLine) {
			this.message = codeLine;
		}

		public boolean getFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

	}

	public String baseUrl = null;
	private int timeout = 0;

	private void sleepASecond() {
		logger.info("Wait a second");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sleepSeconds(int second) {
		logger.info("Wait " + second + " seconds");
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] main) {

	}

	public SeleniumWrapper() {
		this.logger = TestUtils.getLogger();
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/autotest.properties");
		try {
			prop.load(in);
		} catch (IOException e) {

		}

		baseUrl = prop.getProperty("BaseUrl").trim();
		/*username = prop.getProperty("Username").trim();
		password = prop.getProperty("Password").trim();*/
		
		

		if (!baseUrl.endsWith("/")) {
			baseUrl += "/";
		}

		try {
			timeout = Integer.valueOf(prop.getProperty("Timeout").trim());
		} catch (Exception ex) {
			timeout = 30;
			logger.error(ex);
		}

		try {
			isStopBrowser = Boolean.valueOf(prop.getProperty("StopBrower").trim());
		} catch (Exception ex) {
			isStopBrowser = true;
			logger.error(ex);
		}

		if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", basePath + "chromedriver");
		} else if (System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", basePath + "chromedriver.exe");
		}

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		//selenium.addCustomRequestHeader("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));
	}

	public HashMap<String, String> getAllInputs() {
		return map;
	}

	public void pressKey(Keys key) {
		logger.info("Press key: " + key.toString());
		Actions action = new Actions(driver);
		action.sendKeys(key).perform();
	}

	public void pressKey(String key) {
		logger.info("Press key: " + key.toString());
		Actions action = new Actions(driver);
		action.sendKeys(key).perform();
	}

	public void collectNotification() {
		String selector = "css=div.notification";
		try {

			String text = removeHtmlTag(selenium.getAttribute(selector
					+ "@innerHTML"));
			verfifys.add(new VerifyResult("Alert", "The alert info is \""
					+ text + "\"", true));
			logger.info(text);
		} catch (Exception ex) {
			logger.error(ex);
			verfifys.add(new VerifyResult("Alert", "No alert is found!"
					+ ex.getMessage(), false));
		}
	}

	public String getLineInfo() {
		StackTraceElement[] stackTaces = new Throwable().getStackTrace();
		StackTraceElement ste = null;
		for (int i = 0; i < stackTaces.length; i++) {
			ste = stackTaces[i];
			if (!ste.getClassName().contains(this.getClass().getName()))
				break;
		}

		return ste.getFileName() + ": Line " + ste.getLineNumber();
	}

	public void setImplictWait(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public void verifyNotification(AlertType type) {
		verifyNotification("Success: " + type.getMessage());
	}

	public void verifyNotification(String message) {
		String msg = "";
		boolean isSuccessful = false;
		setImplictWait(3);

		int timewait = 0;
		while (timewait < 3) {
			try {
				String text = removeHtmlTag(selenium
						.getAttribute("css=div.notification@innerHTML"));
				if (text.endsWith(".")) {
					text = text.substring(0, text.length() - 1);
				}
				
				if(message.endsWith(".")) {
					message = message.substring(0, message.length() - 1);
				}
				
				if (text.equals(message)) {
					msg = "Verify success!The alert info is " + text;
					isSuccessful = true;
					break;
				} else {
					msg = "Verify fail!We expect is '" + message
							+ "' but the alert info is " + text;
				}
				break;
			} catch (StaleElementReferenceException se) {
				timewait++;
				sleepASecond();
				System.out.println(se.getMessage());
				logger.error(se);
				msg = "Verify fail!" + se.getMessage();
			} catch (Exception ex) {
				// timewait++;
				// sleepASecond();
				if(ex.getMessage().contains("stale element reference")) {
					timewait++;
					sleepASecond();
					System.out.println(ex.getMessage());
					logger.error(ex);
					msg = "Verify fail!" + ex.getMessage();
				}else {
					System.out.println(ex.getMessage());
					msg = "Verify fail!" + ex.getMessage();
					break;
				}
				
			}
		}
		logger.info(msg);
		System.out.println(msg);
		verfifys.add(new VerifyResult("Alert", msg, isSuccessful));
		setImplictWait(timeout);
	}

	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
	private static final String regEx_html = "<[^>]+>";

	public String removeHtmlTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");

		htmlStr = htmlStr.replaceAll("&nbsp;", "");
		return htmlStr.trim();
	}

	public boolean verifyEquals(String selector, String expectedValue) {
		setImplictWait(3);
		String msg = "";
		boolean isSuccessful = false;
		int timewait = 0;
		while (timewait < 3) {
			try {

				String text = removeHtmlTag(
						selenium.getAttribute(selector + "@innerHTML")).trim();
				if (text.equals(expectedValue.trim())) {
					msg = "Verify success!The value of \"" + selector
							+ "\" is '" + text + "'";
					isSuccessful = true;
					break;
				} else {
					msg = "Verify fail!The value of \"" + expectedValue.trim()
							+ "\" is '" + text + "', but we expect '"
							+ expectedValue.trim() + "'";
					timewait++;
				}

			} catch (StaleElementReferenceException se) {
				timewait++;
				sleepASecond();
				System.out.println(se.getMessage());
				logger.error(se);
				msg = "Verify fail!" + se.getMessage();
			} catch (Exception e) {
				msg = "Verify fail!" + e.getMessage();
				System.out.println(e.getMessage());
				break;
			}
		}
		setImplictWait(timeout);
		verfifys.add(new VerifyResult(selector, msg, isSuccessful));
		System.out.println(msg);
		logger.info(msg);
		return isSuccessful;

	}

	public void printVerfifyInfo() {
		int result = 0;
		System.out
				.println("--------------------------------------------------------------");
		for (VerifyResult info : verfifys) {
			System.out.println(info.getMessage() + " --- " + info.getCodeLine()
					+ " --- " + info.getPage());
			if (info.getFlag()) {
				result++;
			}
		}
		System.out
				.println("--------------------------------------------------------------");
		System.out.println(result + " succeed, " + (verfifys.size() - result)
				+ " failed.");
	}

	public Selenium getSelenium() {
		return this.selenium;
	}

	public void windowMaximize() {
		selenium.windowMaximize();
	}

	public String getText(String selector) {
		return selenium.getText(selector);
	}

	public void selectWindow(String windowId) {
		selenium.selectWindow(windowId);
	}

	public void open(String url) {
		logger.info("Open url: " + url);
		selenium.refresh();
		selenium.open(url);
		// driver.get(url);
	}

	public void openAndLogin(String url) {
		openNoSync(url);
		logger.info("Open url: " + url);
		
		/*Keyboard keyboard = ((ChromeDriver)driver).getKeyboard();
		keyboard.sendKeys(username);
		keyboard.pressKey(Keys.TAB);
		keyboard.releaseKey(Keys.TAB);
		keyboard.sendKeys(password);
		keyboard.pressKey(Keys.ENTER);
		keyboard.releaseKey(Keys.ENTER);*/
		
		
		/*Robot robot;
		try {
			robot = new Robot();
			robot.setAutoDelay(200);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			type(robot, username);
			sleepASecond();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			type(robot, password);
			sleepASecond();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException ex) {
			logger.error(ex);
		}*/

	}

	public void openNoSync(String url) {
		logger.info("Open url: " + url);
		final String uri = url;
		new Thread() {
			public void run() {
				driver.get(uri);
			}
		}.start();
		// driver.get(url);
		sleepSeconds(3);
	}

	public static void type(Robot robot, String characters) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection stringSelection = new StringSelection(characters);
		clipboard.setContents(stringSelection, null);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public String getAttribute(String selector) {
		return selenium.getAttribute(selector);
	}

	public String getType(String selector) {
		String type = selenium.getAttribute(selector + "@type");
		switch (type) {
		case "text":
			type = selenium.getAttribute(selector + "@role");
			String dataRole = selenium.getAttribute(selector + "@data-role");
			if (type != null && type.equals("combobox") && dataRole == null)
				return "COMBOBOX";
			else
				return "TEXT";
		case "select-one":
			return "SELECT";
		case "checkbox":
			return "CHECKBOX";
		case "radio":
			return "RADIO";
		case "textarea":
			return "TEXTAREA";
		default:
			return type = selenium.getAttribute(selector + "@tagName");
		}
	}

	public void autoInputByData(String[] selectors, String[] values) {
		for (int i = 0; i < selectors.length; i++) {
			autoInputByData(selectors[i].trim(), values[i].trim());
		}
	}

	public void autoInputByData(String[] selectors, String[] values,
			int startCol) {
		for (int i = startCol; i < selectors.length; i++) {
			autoInputByData(selectors[i].trim(), values[i].trim());
		}
	}

	public void handleInputByType(String selector, String value) {
		String type = getType(selector);
		switch (type) {
		case "TEXT":
		case "TEXTAREA":
			type(selector, value);
			break;
		case "SELECT":
			select(selector, "label=" + value);
			break;
		case "COMBOBOX":
			click(selector);
			pressKey(value);
			sleepSeconds(3);
			pressKey(Keys.DOWN);
			pressKey(Keys.ENTER);
			sleepSeconds(1);
			break;
		default:
			System.out.println("drop data:" + selector + " - " + value);
			logger.info("drop data:" + selector + " - " + value);
			try {
				click(selector);
				// selenium.click(value.trim());
			} catch (Exception ex) {
				logger.error(ex);
			}

			break;
		}
	}

	public void autoInputByData(String selector, String value) {
		System.out.println("selector:" + selector);
		if (!selenium.isElementPresent(selector)) {
			String[] multiWords = value.split("\n");
			for (String word : multiWords) {
				if (word.startsWith("\"") && word.endsWith("\"")) {
					clickCheckBoxByText(word.substring(1, word.length() - 1)
							.trim());
				} else {
					if (word.contains(":")) {
						String[] splits = word.split(":");
						int timeout = 0;
						boolean isExist = false;
						while (timeout < 5) {
							if (!selenium.isElementPresent(splits[0].trim())) {
								System.out.println("can not find " + splits[0]);
								sleepASecond();
							} else {
								isExist = true;
								break;
							}
							timeout++;
						}
						if (isExist) {
							handleInputByType(splits[0].trim(),
									splits[1].trim());
						}
					}
				}
			}
			return;
		}

		handleInputByType(selector, value);
	}

	public void click(String selector) {
		logger.info("Click: " + selector);
		closeAlertTip();
		if (map.containsKey(selector)) {
			logger.info("Has same element: " + selector);
			// sleepSeconds(1);
			map.put(selector, "click");
			waitForElementDisplay(selector);
			waitForElementClickable(selector);
		} else {
			map.put(selector, "click");
			waitForElementDisplay(selector);
			waitForElementClickable(selector);
		}

	}

	public void clickKnockOutCheckbox(String id) {
		if (id.startsWith("id=")) {
			id = id.substring("id=".length(), id.length());
		}
		click("//label[@for='" + id + "']");
	}

	public void doubleClick(String selector) {
		selenium.doubleClick(selector);
	}

	public void navigate(String url) {
		driver.navigate().to(url);
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void goBack() {
		driver.navigate().back();
	}

	public void waitForCondition(String condition) {

		selenium.waitForCondition(condition, "" + timeout * 1000);
	}

	public void chooseCancelOnNextConfirmation() {
		selenium.chooseCancelOnNextConfirmation();
	}

	public void refresh() {
		logger.info("Refresh page!");
		driver.navigate().refresh();
		sleepSeconds(3);
	}

	public void check(String selector) {
		logger.info("Check: " + selector);
		if (map.containsKey(selector)) {
			sleepSeconds(1);
			map.put(selector, "click");
			waitForElementDisplay(selector);
			selenium.check(selector);
		} else {
			map.put(selector, "click");
			waitForElementDisplay(selector);
			selenium.check(selector);
		}
	}

	public void clickCheckBoxByText(String text) {
		click("//label[contains(text(),'" + text + "')]");
	}

	public void clickByFuzzyLinkText(String linkText) {
		logger.info("Click By Fuzzy Link Text: " + linkText);
		click("link=glob:" + linkText);
	}

	public void type(String selector, String value) {
		logger.info("Type: " + selector + " | value: " + value);
		map.put(selector, value);
		waitForElementDisplay(selector);
		selenium.type(selector, value);
	}

	public void select(String selector, String value) {
		logger.info("Select: " + selector + " | Option: " + value);
		if (map.containsKey(selector)) {
			sleepSeconds(1);
		} else {
			map.put(selector, value);
		}
		waitForElementDisplay(selector);
		int timeWait = 0;
		boolean found = false;
		while (timeWait < timeout) {
			try {
				String[] options = selenium.getSelectOptions(selector);
				if (options != null && options.length != 0) {
					for (int i = 0; i < options.length; i++) {
						if (value.startsWith("label=")) {
							if (value.split("label=")[1].equals(options[i])) {
								found = true;
								break;
							}

						} else {
							if (value.equals(options[i])) {
								found = true;
								break;
							}
						}
					}
				}

				if (found)
					break;

				timeWait++;
				sleepASecond();
			} catch (SeleniumException se) {
				timeWait++;
				logger.info("Element " + selector
						+ " element is not attached to the page document!Try "
						+ timeWait + " times");
				//se.printStackTrace();
				System.out.println("wait for " + selector + " --- "	+ getLineInfo());
				System.out.println(se.getMessage());
				sleepASecond();
			} catch (StaleElementReferenceException sere) {
				timeWait++;
				logger.info("Element " + selector
						+ " element is not attached to the page document!Try "
						+ timeWait + " times");
				//sere.printStackTrace();
				System.out.println("wait for " + selector + " --- "	+ getLineInfo());
				System.out.println(sere.getMessage());
				sleepASecond();
			}

		}

		timeWait = 0;
		while (timeWait < timeout) {
			try {
				selenium.select(selector, value);
				timeWait++;
				break;
			} catch (SeleniumException se) {
				timeWait++;
				logger.info("Element " + selector
						+ " element is not attached to the page document!Try "
						+ timeWait + " times");
				System.out.println("wait for " + selector + " --- "	+ getLineInfo());
				System.out.println(se.getMessage());
				sleepASecond();
			} catch (StaleElementReferenceException sere) {
				timeWait++;
				logger.info("Element " + selector
						+ " element is not attached to the page document!Try "
						+ timeWait + " times");
				System.out.println("wait for " + selector + " --- "	+ getLineInfo());
				System.out.println(sere.getMessage());
				sleepASecond();
			}
		}

	}

	public String[] getSelectOptions(String selector) {
		return selenium.getSelectOptions(selector);
	}

	public int getTimeout() {
		return timeout;
	}

	public void waitForPageToLoad(String timeout) {
		logger.info("Wait for page to load for as long as " + timeout
				+ " milliseconds");
		selenium.waitForPageToLoad(timeout);
	}

	public void selectByIdAndIndex(String selector, int index) {
		logger.info("Select: " + selector + " | OptionIndex :" + index);
		waitForElementDisplay(selector);
		new Select(driver.findElement(By.id(selector))).selectByIndex(index);
	}

	public WebElement waitElementPresentById(String id) {
		return new WebDriverWait(driver, 30).until(ExpectedConditions
				.presenceOfElementLocated(By.id(id)));
	}

	/**
	 * wait for the element display
	 * 
	 * @param locator
	 * @param timeout
	 *            seconds
	 * @return
	 */
	public boolean waitForElementDisplay(String locator, int timeout) {
		logger.info("Wait for element :" + locator
				+ " is visible for as long as  " + timeout + " seconds");
		int timeWait = 0;
		while (timeWait < timeout * 1000) {
			try {
				if (selenium.isVisible(locator)) {
					return true;
				}
				sleepASecond();
				System.out.println("Element " + locator + " not found!");
			} catch (Exception ex) {
				sleepASecond();
				ex.printStackTrace();
			}
			timeWait += 1000;
		}
		return false;
	}

	public void waitForElementClickable(String locator) {
		int wait = 0;
		do {
			try {
				selenium.click(locator);
				wait = 0;
			} catch (StaleElementReferenceException sere) {
				wait++;
				logger.info("Element " + locator + " is not clickable now!Try "
						+ wait + " times");
				//sere.printStackTrace();
				System.out.println("wait for " + locator + " --- "	+ getLineInfo());
				System.out.println(sere.getMessage());
				sleepASecond();
			} catch (Exception ex) {
				if (ex.getMessage().contains("Element is not clickable")) {
					wait++;
					logger.info("Element " + locator + " is not clickable now!Try " + wait + " times");
					//ex.printStackTrace();
					sleepASecond();
				}
				System.out.println("wait for " + locator + " --- "	+ getLineInfo());
				System.out.println(ex.getMessage());
				logger.error(ex);

				/*
				 * if(wait == timeout) { throw ex; }
				 */
			}
		} while (wait > 0 && wait < timeout);
	}

	public boolean waitForElementDisplay(String locator) {
		int timeWait = 0;
		while (timeWait < timeout) {
			try {
				if (selenium.isVisible(locator)) {
					return true;
				}
				sleepASecond();
				logger.info("wait for " + locator);
				System.out.println("wait for " + locator);
			} catch (Exception ex) {
				if (timeWait == timeout - 1) {
					// throw ex;
				} else {
					System.out.println("wait for " + locator + " --- "
							+ getLineInfo());
					System.out.println(ex.getMessage());
					logger.info(ex.getMessage());
					sleepASecond();
				}
			}
			timeWait++;
		}

		logger.info("Wait for element :" + locator
				+ " is visible for as long as  " + timeWait / 1000 + " seconds");
		return false;
	}

	public boolean waitForElementHidden(String locator) {
		logger.info("Wait for element :" + locator
				+ " is hidden for as long as  " + timeout + " seconds");
		int timeWait = 0;
		while (timeWait < timeout * 1000) {
			try {
				if (!selenium.isElementPresent(locator)
						|| !selenium.isVisible(locator)) {
					return true;
				}
				sleepASecond();
			} catch (Exception ex) {
				System.out.println("wait for " + locator + " hiding... --- "
						+ getLineInfo());
				System.out.println(ex.getMessage());
				logger.info(ex.getMessage());
				sleepASecond();
			}
			timeWait += 1000;
		}
		return false;
	}

	public boolean isVisible(String selector) {
		return selenium.isVisible(selector);
	}

	public boolean isElementPresent(String selector) {
		return selenium.isElementPresent(selector);
	}

	// useless
	public void waitElementClickableById(String id) {
		closeAlertTip();
		logger.info("Wait for element " + id
				+ " to be clickable for as long as 30 seconds");
		try {
			new WebDriverWait(driver, timeout).until(
					ExpectedConditions.elementToBeClickable(By.id(id))).click();
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public void waitUntilCondition() {
		(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {

				return d.getTitle().toLowerCase().startsWith("cheese!");
			}
		});
	}

	public String[] getAllFields() {
		return selenium.getAllFields();
	}

	public void clickElementsByName(String name) {
		closeAlertTip();
		// sleepASecond();
		java.util.List<WebElement> elements = driver
				.findElements(By.name(name));
		for (WebElement element : elements) {
			try {
				element.click();
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex);
			}

		}
	}

	public void closeAlertTip() {
		runScriptNoLog("$('#messages').hide()");
	}

	public WebElement findElementByScript(String script) {
		return (WebElement) ((JavascriptExecutor) driver).executeScript(script);
	}

	public void runScript(String script) {
		logger.info("Run javascript :" + script);
		try {
			((JavascriptExecutor) driver).executeScript(script);
			sleepASecond();
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}

	}

	public void runScriptNoLog(String script) {
		try {
			((JavascriptExecutor) driver).executeScript(script);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public String getValueByScript(String script) {
		return String.valueOf(((JavascriptExecutor) driver)
				.executeScript(script));
	}

	public void comfirmAlert() {
		try {
			logger.info("Alert: choose Yes");
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public void cancelAlert() {
		try {
			logger.info("Alert: choose No");
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (Exception ex) {
			logger.error(ex);
		}

	}

	public void stop() {
		try {
			if (isStopBrowser) {
				map.clear();
				driver.quit();
			}
		} catch (Exception ex) {
			logger.error(ex);
		}

	}

	public String[][] getExcelData(String fileName) throws IOException {
		return getExcelData(fileName, null);
	}

	public String[][] getExcelData(String fileName, String sheetName)
			throws IOException {

		String basePath = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		File file = new File(basePath + File.separatorChar + fileName);

		List<String[]> results = new ArrayList<String[]>();
		FileInputStream fis = new FileInputStream(file);
		// POIFSFileSystem POIStream = new POIFSFileSystem(fis);
		Workbook workBook = null;
		if (fileName.endsWith(".xls")) {
			workBook = new HSSFWorkbook(fis);
		} else {
			workBook = new XSSFWorkbook(fis);
		}
		Sheet sheet = null;
		if (sheetName == null) {
			sheet = workBook.getSheetAt(0);
		} else {
			sheet = workBook.getSheet(sheetName);
		}

		int rowSize = sheet.getLastRowNum();
		System.out.println("The row size is: " + rowSize);

		for (int rowIndex = 0; rowIndex <= rowSize; rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			int colSize = row.getLastCellNum();

			Cell firstCell = row.getCell(0);
			if (firstCell.getStringCellValue().trim().equals("")) {
				continue;
			}

			String[] values = new String[colSize];
			Arrays.fill(values, "defaultvalue");
			for (int columnIndex = 0; columnIndex < colSize; columnIndex++) {
				String value = "";
				Cell cell = row.getCell(columnIndex);
				if (cell != null) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								value = new SimpleDateFormat("MM/dd/yyyy")
										.format(date);
							} else {
								value = "";
							}
						} else {
							value = new DecimalFormat("0").format(cell
									.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_FORMULA:
						if (!cell.getStringCellValue().equals("")) {
							value = cell.getStringCellValue();
						} else {
							value = cell.getNumericCellValue() + "";
						}
						break;

					case Cell.CELL_TYPE_BLANK:
						break;
					case Cell.CELL_TYPE_ERROR:
						value = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						value = (cell.getBooleanCellValue() == true ? "Y" : "N");
						break;
					default:
						value = "";
					}
				}
				values[columnIndex] = value.trim();

			}
			results.add(values);
		}
		fis.close();

		String[][] returnArray = new String[results.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = results.get(i);
		}
		return returnArray;

	}

}
