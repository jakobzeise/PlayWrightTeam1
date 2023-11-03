package day5;

import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Videos {
    public static void main(String[] args) {


        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000)))
        {
            Path videoPath = Paths.get("./videos");
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(videoPath));
            Page page = context.newPage();
            page.navigate("https://google.com");
        }





    }
}
