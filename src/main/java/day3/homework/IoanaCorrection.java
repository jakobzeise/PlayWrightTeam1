package day3.homework;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class IoanaCorrection {


    public static void main(String[] args) {

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(250);
        try (
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(options);
                Page page = browser.newPage()
        ) {


            page.navigate("https://example.test.recognia.com/serve.shtml?page=login");

            System.out.println(page.title());
            Locator loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
            Locator username = page.getByPlaceholder("Enter your user ID");
            username.fill("TESTAUTOMATION");
            Locator password = page.getByPlaceholder("Password");
            password.fill("Test1Test23456789!");
            loginButton.click();

            Locator products = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Products"));
            products.click();

            Locator goToMarketBuzz = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Market Buzz").setExact(true));
            goToMarketBuzz.click();

            Locator clickTable = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Table"));
            clickTable.click();

            Locator clickBubbles = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Bubbles"));
            clickBubbles.click();

            page.getByText("Cryptos").click();

            page.getByText("Currencies").click();

            page.getByText("Commodities").click();

            page.getByText("Indices").click();

            page.getByText("Stocks").click();

            page.getByText("Countries").click();

            List<Locator> countries = page.getByRole(AriaRole.MENUITEMCHECKBOX).all();
            for (Locator l : countries) {
                System.out.println(l.textContent() + "selected :  " + l.getAttribute("aria-selected"));
                if (l.getAttribute("aria-selected").equals("true")) {
                    l.click();
                }
            }

//            page.getByText("USA", new Page.GetByTextOptions().setExact(true)).click(); // deselect
//            page.getByText("Singapore", new Page.GetByTextOptions().setExact(true)).click(); // deselect
//            page.getByText("Vietnam", new Page.GetByTextOptions().setExact(true)).click(); // deselect
//            page.getByText("Philippines", new Page.GetByTextOptions().setExact(true)).click(); // deselect
//            page.getByText("Portugal", new Page.GetByTextOptions().setExact(true)).click(); // deselect

            page.getByText("Germany", new Page.GetByTextOptions().setExact(true)).click(); // select
//            page.locator("div[class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']").click();
            Locator overlay = page.locator(".cdk-overlay-backdrop");
            overlay.click();

            page.getByText("Sectors & Industries").click();

            page.getByText("Utilities", new Page.GetByTextOptions().setExact(true)).click(); // deselect
            page.getByText("Healthcare", new Page.GetByTextOptions().setExact(true)).click(); // select

            page.getByText("Bubble Size", new Page.GetByTextOptions().setExact(true)).click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions()).nth(4).click();

            page.getByText("Bubble Color").click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("News Sentiment")).click();

            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
