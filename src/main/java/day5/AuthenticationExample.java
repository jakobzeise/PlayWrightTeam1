package day5;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.HttpCredentials;

public class AuthenticationExample {

    public static void main(String[] args) {


        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));



        Page page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/basic_auth");

        Page page2 = context.newPage();
        page2.navigate("https://the-internet.herokuapp.com/basic_auth");

        BrowserContext browserContext = browser.newContext();
        browserContext.pages();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
