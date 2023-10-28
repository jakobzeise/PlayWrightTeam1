package day3;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class PageNavigation {

    public static void main(String[] args) {


        try (
                Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(5000).setHeadless(false));

            Page page = browser.newPage();


            Response response = page.navigate("https://tradingcentral.com/thiswebsitedoesnotexist");
//            Response navigate = page.navigate("https://asldkfjasdölfkjasdöflkj.com");

            System.out.println("navigate.status() = " + response.status());
//            System.out.println(response.status());
            // 404


            page.goForward();
            page.goBack();
            page.navigate("");
            page.reload();






        }
    }
}
