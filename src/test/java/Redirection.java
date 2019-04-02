import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.net.URL;

public class Redirection {
    public static ChromeDriver driver;

    @Test(priority=2)
    public void RedirectionTest() {
        System.out.println("Проверка редиректа с http://onetwotrip.com");
        System.setProperty("webdriver.chrome.driver", "target/classes/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://onetwotrip.com");
        String Url = driver.getCurrentUrl();
        int Check=0;
        if (Url.contains("https://www.onetwotrip.com/")) ++Check;
        Assert.assertFalse("Редирект отсутсвует", Check == 0);
        System.out.println ("Редирект на URL "+Url+" произведен");
        driver.quit();
        System.out.println("Завершение работы браузера");
    }
}
