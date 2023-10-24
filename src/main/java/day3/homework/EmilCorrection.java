package day3.homework;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EmilCorrection {

    public static void main(String[] args) {
        try (
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                Page page = browser.newPage();
        ) {
            page.navigate("https://example.test.recognia.com/serve.shtml?page=login");
            page.getByPlaceholder("Enter your user ID").click();
            page.getByPlaceholder("Enter your user ID").fill("TESTAUTOMATION");
            page.getByPlaceholder("Password").click();
            page.getByPlaceholder("Password").fill("Test1Test23456789!");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Products")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Panoramic View").setExact(true)).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log off")).click();
        }
    }
}
