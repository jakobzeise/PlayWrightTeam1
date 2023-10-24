package day3.homework;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PageAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Emil {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(3000));

            //assertThat - will import after first usage (alt-enter);
            //check https://playwright.dev/java/docs/test-assertions for syntax help;
            //use assertions after defining locators inside the tested page;
            Page page = browser.newPage();
            page.navigate("https://example.test.recognia.com/serve.shtml?page=login");
            assertThat(page).hasTitle("MyTradingCentral",
                    new PageAssertions.HasTitleOptions().setTimeout(1000));
            System.out.println(page.title());

            Thread.sleep(5000);
            page.locator("[placeholder='Enter your user ID']").click();
            assertThat(page.locator("[placeholder='Enter your user ID']")).isVisible();
            System.out.println("Selected User-Id Field");

            page.fill("[placeholder='Enter your user ID']", "TESTAUTOMATIONlalala");
            System.out.println("Type - in wrong user credentials: TESTAUTOMATIONlalala");


            page.fill("[placeholder='Enter your user ID']", "TESTAUTOMATION");
            System.out.println("Type in TESTAUTOMATION user");

            page.locator("[placeholder='Password']").click();
            assertThat(page.locator("[placeholder='Password']")).isVisible();
            System.out.println("Selected Password Field");

            page.fill("[placeholder='Password']", "Test1Test");
            System.out.println("Entered password: Test1Test - wrong password");

            //page.locator("text=Login").click();

            page.fill("[placeholder='Password']", "Test1Test23456789!");
            System.out.println("Entered correct password: Test1Test23456789!");

            page.locator("text=Login").click();
            assertThat(page.locator("text=Login")).isVisible();
            System.out.println("Login Button Pressed");

            //this is where it crashes - I have no idea how to link the products dropdown element
            //commented the selector and tried using the "Products button, by ID:

            page.locator("[text()='Products']").click();
            assertThat(page.locator("div.w-icon-dropdown-toggle")).hasClass("div.w-icon-dropdown-toggle");
            System.out.println("Click Top Products Dropdown-Menu");

            //second try to select/assert still crashes
            page.locator("#w-dropdown-toggle-0").click();
            assertThat(page.locator("#w-dropdown-toggle-0")).hasId("w-dropdown-toggle-0");
            System.out.println("Press Products Button");

            page.locator("text=Log off").click();
            assertThat(page.locator("text=Log off")).hasText("Log off");
            System.out.println("Pressing Log Off Button");

            playwright.close();
            System.out.println("Browser Page Close");
        } catch (Exception e) {

            System.out.println("An Error Occurred while running the test script");
        }

    }

}
