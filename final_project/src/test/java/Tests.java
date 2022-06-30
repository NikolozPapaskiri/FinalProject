import Data.TokenOutput;
import Steps.UserTestSteps;
import Steps.WebPageSteps;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

@Epic("whole test class")
public class Tests {

    @BeforeClass
    public void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @Step("all tests")
    @Test
    public void mainTest() {
        Map<String, Object> data = new HashMap<>();
        data.put("userName", "Nikoloz" +  Math.random()*(100000-10+1)+10);
        data.put("password", "Asd-12345%6");

        Assert.assertEquals(UserTestSteps.createUser(data).books.length, 0);

        TokenOutput response = UserTestSteps.generateToken(data);
        Assert.assertEquals(response.status, "Success");
        Assert.assertEquals(response.result, "User authorized successfully.");
        Assert.assertTrue(UserTestSteps.checkAuthorized(data));

        open("https://demoqa.com/login");
        WebPageSteps.webPageTest(data);
    }
}
