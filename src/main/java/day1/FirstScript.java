package day1;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FirstScript {

    public static void main(String[] args) {


        // Auto Closeable
        try (Playwright playwright = Playwright.create()) {

            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000);

            Browser browser = playwright.chromium().launch(launchOptions);

            Page page = browser.newPage();
            page.navigate("https://www.google.com");
            page.navigate("https://playwright.dev/java/docs/intro");
            page.navigate("https://www.google.com");
            page.navigate("https://playwright.dev/java/docs/intro");
            page.navigate("https://www.google.com");
            page.navigate("https://playwright.dev/java/docs/intro");
            page.navigate("https://www.google.com");
            page.navigate("https://playwright.dev/java/docs/intro");
            page.navigate("https://www.google.com");
            page.navigate("https://playwright.dev/java/docs/intro");
            page.navigate("https://www.google.com");
            page.navigate("https://playwright.dev/java/docs/intro");


            Path path = Paths.get("screenshot.png");
            Page.ScreenshotOptions options = new Page.ScreenshotOptions().setPath(path).setFullPage(true);
            page.screenshot(options);

        }

    }
}
