package day5;

import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class MultiplePages {

    public static void main(String[] args) {


        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));
        BrowserContext context1 = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));

        List<Page> pages = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            pages.add(browser.newPage());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        pages.get(0).navigate("https:wikipedia.org");
        pages.get(1).navigate("https:tradingcentral.com");
        pages.get(1).navigate("https:tradingcentral.com");

        String information = "something";


    }
}
