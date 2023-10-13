package day3;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.opentest4j.AssertionFailedError;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class AssertionsExample {


    public static void main(String[] args) {

        try (
                Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(3000).setHeadless(false));

            Page page = browser.newPage();

            page.navigate("http://127.0.0.1:5500/RadioButton.html");


            Locator radioButton = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("HTML"));
            assertThat(radioButton).isVisible();






        } catch (AssertionFailedError error) {
            FileOperations.writeToFile(error.getMessage());
        } finally {
            System.out.println("Rest of the program");
        }




    }



}

