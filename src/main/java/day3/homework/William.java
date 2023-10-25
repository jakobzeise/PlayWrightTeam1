package day3.homework;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class William {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(List.of("--start-maximized"));

        Browser browser = playwright.chromium().launch(launchOptions);
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        browserContext.clearCookies();
        Page page = browserContext.newPage();
        String url, adminUserName, adminPassWord, logString, testName;
        url = "https://example.test.recognia.com/serve.shtml?page=login";
        adminUserName = "TESTAUTOMATION";
        adminPassWord = "Test1Test23456789!";
        logString = "";
        testName = getTestName();

        page.navigate(url);
        enterText(page, "User ID", adminUserName);
        enterText(page, "Password", adminPassWord);
        clickButton(page, "Login");

        page.reload();

        logString += verifyTextOnPage(page, "Our diverse analytical scope, broad market coverage, and flexible integration methods make it easy to support your investors in the moments that matter.", testName);
        clickButton(page, "Products");
        clickLink(page, "Technical Insight");
        // hardcode time out as not sure how to wait for page to load properly at the moment

        page.waitForCondition(() -> page.getByLabel("Screener").isVisible());

        clickLabel(page, "Screener");

        logString += verifyLinkOnPage(page, " Alert Center ", testName);
        logString += verifyLinkOnPage(page, "Homepage", testName);
        checkBoxSelection(page, "Optionable", true);

        page.locator("button").filter(new Locator.FilterOptions().setHasText("search")).click();
        page.getByRole(AriaRole.SEARCHBOX).fill("Tesla");
        page.locator(".tc-new-layover").click();



        clickLink(page, "Watchlists");
        clickButton(page, "New Watchlist");
        page.getByLabel("Enter a name to describe your new watchlist.").fill("Test");
        clickButton(page, "Add");

        pause();

        generateTestLog(logString, testName);
        playwright.close();
    }
    public static void pause(){
        try {
            Thread.sleep(5000);
        } catch (Exception ignored) {

        }
    }


    public static void checkBoxSelection(Page page, String checkBoxLabel, boolean expectState) {
        Locator checkBox = page.locator("//div[normalize-space()='" + checkBoxLabel + "']");
        if (expectState) {
            checkBox.check();
        } else {
            checkBox.uncheck();
        }
    }


    // TODO
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

    // TODO
    public static void selectDropDownOption(Page page, String dropDownFieldID, String targetDropDownOption) {
        try {
            page.selectOption("#" + dropDownFieldID, targetDropDownOption);
        } catch (Exception e) {
            System.out.println("Drop down field or target value is not found on the page!");
        }
    }

    //TODO
    public static void clickImg(Page page, String imageText) {
        Locator image = page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName(imageText));
        if (image.count() > 0) {
            image.click();
        } else {
            System.out.println("Link '" + imageText + "' is not found on the page!");
        }
    }

    public static String getTestName() {
        return William.class.getSimpleName();
    }

    public static void clickLabel(Page page, String labelText) {
        Locator label = page.getByLabel(labelText, new Page.GetByLabelOptions().setExact(true));
        if (label.count() == 0) {
            System.out.println("Label '" + labelText + "' is not found on page!");
        } else {
            label.click();
        }
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


    public static void generateTestLog(String logString, String testName) {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentTime = formatter.format(date);
        String fileName;
        String path = "src\\main\\java\\day3\\homework\\Logs";
        if (new File(path).mkdirs()) {
            System.out.println("Directory 'Logs' created");
        }
        if (logString.contains("error")) {
            fileName = testName + "_" + currentTime + "-error.txt";
        } else {
            fileName = testName + "_" + currentTime + ".txt";
        }
        try {
            File file = new File(path + "\\" + fileName);
            if (file.createNewFile()) {
                System.out.println("File '" + fileName + "' created");
            }
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


        String path = "src\\main\\java\\day3\\homework\\Screenshots\\";
        if (new File(path).mkdirs()) {
            System.out.println("Directory 'Logs' created");
        }
        String fileName = testName + "_" + currentTime + ".png";
        page.screenshot(new Page.ScreenshotOptions().setPath(Path.of(path + fileName)).setFullPage(true));
        return fileName;
    }

    public static String verifyTextOnPage(Page page, String textValue, String testName) {
        if (page.locator("p", new Page.LocatorOptions().setHasText(textValue)).isVisible())
            return "'" + textValue + "' is found on the page.\n";
        String screenshotFileName = takeScreenShot(page, testName);
        System.out.println("error - '" + textValue + "' is NOT found on the page! Screenshot - " + screenshotFileName + "\n");
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


//Questions:
// search field - img or button or what?
//      page.locator("button").filter(new Locator.FilterOptions().setHasText("search")).click();

// how to locate new watchlist input field?
//      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New Watchlist")).click();
//      page.getByLabel("Enter a name to describe your new watchlist.").fill("Test");
//      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();


// how to wait for page to load? TV, screener
//      page.waitForCondition(() -> page.getByLabel("Screener").isVisible());
