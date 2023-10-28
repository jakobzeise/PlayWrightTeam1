package day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.Collection;
import java.util.function.Consumer;

public class Dialogs {
    public static void main(String[] args) {


        try (
                Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(3000).setHeadless(false));


            Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
            BrowserContext browserContext = browser.newContext(contextOptions);

            Page page = browserContext.newPage();
            page.navigate("https://letcode.in/alert");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Consent").setExact(true)).click();

            Consumer<Dialog> dialogConsumer = dialog -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("dialog.message() = " + dialog.message());
                dialog.accept();
            };



            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Simple Alert")).click();


            page.onceDialog(dialog -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                dialog.dismiss();
                System.out.println("dialog.message() = " + dialog.message());
            });
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Prompt Alert").setExact(true)).click();

            page.onDialog(dialog -> dialog.accept("Test"));

            Thread.sleep(5000);





        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
