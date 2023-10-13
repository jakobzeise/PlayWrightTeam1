package day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class ButtonClick {

    public static void main(String[] args) {


        try(Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(2000).setHeadless(false));

            Page page = browser.newPage();

            page.navigate("http://127.0.0.1:5500/Test2.html");


            Locator subscribe = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Subscribe"));
            subscribe.check();
            subscribe.check();
            subscribe.check();
            subscribe.check();
            subscribe.check();

            Locator password = page.getByLabel("Password");
            password.fill("test");

            Locator email = page.getByPlaceholder("test@gmail.com");
            email.fill("MyEmailAddress");

            page.getByRole(AriaRole.LINK);

            email.fill(Text.password);

            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
