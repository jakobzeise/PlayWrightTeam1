package day3.homework;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
public class Ioana {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create() ) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
                    //slomow ca sa mearga mai incet  new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );
            Thread.sleep(2000);
            Page page = browser.newPage();
            Thread.sleep(2000);
            page.navigate("https://example.test.recognia.com/serve.shtml?page=login");

            System.out.println(page.title());
            Locator loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
            Locator username = page.getByPlaceholder("Enter your user ID");
            username.fill("TESTAUTOMATION");
            Locator password =  page.getByPlaceholder("Password");
            password.fill("Test1Test23456789!");
            loginButton.click();

            Locator products = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Products"));
            products.click();

            Locator goToMarketBuzz =  page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Market Buzz").setExact(true));
            goToMarketBuzz.click();

            Thread.sleep(10000);
            //@Jakob how can i wait for the whole page to load?
            //page.waitForSelector ("mdc-slider__track--active");

            Locator clickTable = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Table"));
            clickTable.click();

            Locator clickBubbles = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Bubbles"));
            clickBubbles.click();

            Locator goToCryptos =  page.getByText("Cryptos");
            goToCryptos.click();
            //@Jakob how can i wait for the whole page to load?
            Locator goToCurrencies =  page.getByText("Currencies");
            goToCurrencies.click();

            //@Jakob how can i wait for the whole page to load?
            Locator goToCommodities =  page.getByText("Commodities");
            goToCommodities.click();

            //@Jakob how can i wait for the whole page to load?
            Locator goToIndices =  page.getByText("Indices");
            goToIndices.click();

            //@Jakob how can i wait for the whole page to load?
            //no themes for this user
            //Locator goToThemes =  page.getByText("Themes");
            //goToThemes.click();

            //@Jakob how can i wait for the whole page to load?
            Locator goToStocks =  page.getByText("Stocks");
            goToStocks.click();

            // Locator selectCountry =  page.getByText("Countries");
            // selectCountry.click();

            Thread.sleep(2000);

            // Locator selectCountry1 =  page.getByText("Singapore");
            // selectCountry1.click();


            // @jakob how to scroll in the countries pick list i tried the below it is not working
            //page.mouse().wheel(0,200);
            //Locator selectCountry2 =  page.getByText("Canada");
            //selectCountry2.click();

            Locator selectSector =  page.getByText("Sectors & Industries");
            selectSector.click();

            Locator selectSpecificSector =  page.getByText("Utilities");
            selectSpecificSector.click();

            Locator selectSortBy =  page.getByText("Sort By");
            selectSortBy.click();

            Locator selectSortByTB =  page.getByText("Top Buzzing");
            selectSortByTB.click();


            Locator selectIndicator =  page.getByText("Indicator");
            selectIndicator.click();

            Locator selectSortByNews =  page.getByText("News Sentiment");
            selectSortByNews.click();


        } catch(InterruptedException e){
            System.out.println("a thread error occured");
        }

    }
}
