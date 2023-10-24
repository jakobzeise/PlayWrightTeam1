package day3.homework;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class William {

    public static void main(String[] args) {


        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500).setArgs(List.of("--start-maximized"));
        Browser browser = playwright.chromium().launch(launchOptions);
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        String url, adminUserName, adminPassWord, logString, testName;
        //url = "https://example.test.recognia.com/serve.shtml?page=login";
        //adminUserName = "TESTAUTOMATION";
        //adminPassWord = "Test1Test23456789!";
        url = "https://example.dev.recognia.com/";
        adminUserName = "yueyong.huang@tradingcentral.com";
        adminPassWord = "JE4k930zjaUlffK";
        logString = "";
        testName = getTestName();

        page.navigate(url);
        enterText(page, "User ID", adminUserName);
        enterText(page, "Password", adminPassWord);
        clickButton(page, "Login");
        logString += verifyTextOnPage(page, "Our diverse analytical scope, broad market coverage, and flexible integration methods make it easy to support your investors in the moments that matter.", testName);
        clickButton(page, "Products");
        clickLink(page, "Technical Insight");
        pause(); // hardcode time out as not sure how to wait for page to load properly at the moment
        clickLink(page, " Screener ");
        pause();
        logString += verifyLinkOnPage(page, " Alert Center ", testName);
        logString += verifyLinkOnPage(page, "Google homepage", testName);
        checkBoxSelection(page, "Optionable", true);
        generateTestLog(logString, testName);
        playwright.close();

    }

    public static void checkBoxSelection(Page page, String checkBoxLabel, Boolean expectState) {
        Locator checkBox = page.locator("//div[normalize-space()='" + checkBoxLabel + "']");
        if (expectState) {
            checkBox.check();
        } else {
            checkBox.uncheck();
        }
    }

    public static void clickRadioButton(Page page, String radioButtonOption) {
        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Create your own Gmail address")).click();
        /*
        boolean flag = false;
        Locator radioList = page.getByRole(AriaRole.RADIO);
        for (int i = 0; i < radioList.count(); i++) {
            Locator radio = radioList.nth(i);
            String labelledbyid = radio.getAttribute("aria-labelledby");
            Locator elementWithText = page.locator(":has-text('"+radioButtonOption+"')");
            for (int j = 0; j < elementWithText.count(); j++) {
                Locator radioText = elementWithText.nth(j);
                if (labelledbyid.equals(radioText.getAttribute("id"))){
                    radio.click();
                    flag = true;
                    break;
                }
            }
            break;
        }
        if (flag == false){
            System.out.println("Target radio button '"+radioButtonOption+"' is not found on the page!");
        }
         */
    }

    public static void selectDropDownOption(Page page, String dropDownFieldID, String targetDropDownOption) {
        try {
            page.selectOption("#" + dropDownFieldID, targetDropDownOption);
        } catch (Exception e) {
            System.out.println("Drop down field or target value is not found on the page!");
        }
    }

    public static void clickImg(Page page, String imageText) {
        Locator image = page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName(imageText));
        if (image.count() > 0) {
            image.click();
        } else {
            System.out.println("Link '" + imageText + "' is not found on the page!");
        }
    }

    public static String getTestName() {
        return Object.class.getEnclosingClass().getSimpleName();
    }

    public static void clickLink(Page page, String linkText) {
        Locator link = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(linkText));
        if (link.count() > 0) {
            link.first().click();
        } else {
            System.out.println("Link '" + linkText + "' is not found on the page!");
        }
    }

    public static void clickButton(Page page, String buttonText) {
        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(buttonText));
        if (button.count() > 0) {
            button.first().click();
        } else {
            System.out.println("Button '" + buttonText + "' is not found on the page!");
        }
    }

    public static void enterText(Page page, String fieldTitle, String textContent) {
        Locator textField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(fieldTitle)); // match with either placeholder value or data-name value
        if (textField.count() > 0) {
            textField.first().fill(textContent);
        } else {
            System.out.println("Input field '" + fieldTitle + "' is not found on the page!");
        }
    }


    public static void pause() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
    }

    public static void generateTestLog(String logString, String testName) {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentTime = formatter.format(date);
        String folderPath = "C:\\Users\\Yueyong Huang\\IdeaProjects\\AutomatedTestReport\\" + testName;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String fileName;
        if (logString.contains("error")) {
            fileName = testName + "_" + currentTime + "-error.txt";
        } else {
            fileName = testName + "_" + currentTime + ".txt";
        }

        try {
            File file = new File(folderPath, fileName);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(logString);
            fileWriter.close();
        } catch (Exception ignored) {

        }
    }

    public static String takeScreenShot(Page page, String testName) {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentTime = formatter.format(date);

        String folderPath = "C:\\Users\\Yueyong Huang\\IdeaProjects\\AutomatedTestReport\\" + testName;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String fileName = testName + "_" + currentTime + ".png";
        Path path = Paths.get(folderPath, fileName);
        page.screenshot(new Page.ScreenshotOptions().setPath(path).setFullPage(true));
        return fileName;
    }

    public static String verifyTextOnPage(Page page, String textValue, String testName) {
        List<Locator> elements = page.locator(":has-text('" + textValue + "')").all();
        if (!elements.isEmpty()) {
            for (Locator element : elements) {
                if (element.isVisible()) {
                    return "'" + textValue + "' is found on the page.\n";
                }
            }
        }
        String screenshotFileName = takeScreenShot(page, testName);
        return "error - '" + textValue + "' is NOT found on the page! Screenshot - " + screenshotFileName + "\n";
    }

    public static String verifyLinkOnPage(Page page, String linkLabel, String testName) {
        List<Locator> links = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(linkLabel)).all();
        if (!links.isEmpty()) {
            for (Locator link : links) {
                if (link.isVisible()) {
                    return "Link '" + linkLabel + "' is found on the page.\n";
                }
            }
        }
        String screenshotFileName = takeScreenShot(page, testName);
        return "error - Link '" + linkLabel + "' is NOT found on the page! Screenshot - " + screenshotFileName + "\n";
    }
}

