package day3.homework;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.opentest4j.AssertionFailedError;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Alex {

    public static void main(String[] args) {

        try (
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(3000).setHeadless(false));
                Page page = browser.newPage()
        ) {

            page.navigate("https://google.com");

            Locator object1 = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Gmail"));

            //  assertThat(object1).isFocused();

            assertThat(page).hasTitle("Google");
            System.out.println("still working");


        } catch (AssertionFailedError error) {
            error.printStackTrace();
        } finally {
            System.out.println("Rest of the program");
        }


    }
}
