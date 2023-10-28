package day4;

import com.microsoft.playwright.*;

public class FullScreen {

    public static void main(String[] args) {

        try (
                Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(3000).setHeadless(false));



            Browser.NewContextOptions contextOptions = new Browser.NewContextOptions().setViewportSize(500, 500);
            BrowserContext browserContext = browser.newContext(contextOptions);

            Page page = browserContext.newPage();
            Thread.sleep(50000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
