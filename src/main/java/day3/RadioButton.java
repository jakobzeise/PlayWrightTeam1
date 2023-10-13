package day3;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class RadioButton {

    public static void main(String[] args) {


        try (
                Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(5000).setHeadless(false));

            Page page = browser.newPage();

            page.navigate("http://127.0.0.1:5500/RadioButton.html");

            Locator radioButton = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("HTML"));

            radioButton.check();




        }
    }
}
