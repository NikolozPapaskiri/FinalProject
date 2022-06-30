package Steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class WebPageSteps {

    public static void webPageTest(Map<String, Object> data) {
        //დალოგინება
        $("#userName").sendKeys(data.get("userName").toString());
        $("#password").sendKeys(data.get("password").toString());
        $("#login").click();

        //ამოწმებს username-ის სისწორეს.
        $("#userName-value").shouldHave(Condition.text(data.get("userName").toString()));

        //წიგნის დამატება
        $("#gotoStore").click();
        $x("//span[starts-with(@id, 'see-book-You')]").click();
        $x("//button[starts-with(text(), 'Add To Your Collection')]").click();

        Selenide.sleep(10000);
        //გადასვლა პროფილზე და წიგნის შემოწმება
        $x("//li[@id = 'item-3']/span[text()='Profile']").scrollIntoView(true);
        $x("//li[@id = 'item-3']/span[text()='Profile']").click();
        $x("//span[starts-with(@id, 'see-book-You')]").$("a").shouldHave(Condition.text("You Don't Know JS"));
    }
}
