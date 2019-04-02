import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.options;
import static io.restassured.RestAssured.get;


public class RequestApi {

    @Test(priority=0)
    public void HealthCheck() {
        System.out.println("Проверка доступности сервиса https://www.onetwotrip.com/_hotels/api/suggestRequest");
        int a = 0;
        Response getOptions = options("https://www.onetwotrip.com/_hotels/api/suggestRequest");
        if (getOptions.statusCode() == 200) ++a;
        Assert.assertFalse("Сервис недоступен", a == 0);
        System.out.println("Сервис доступен");
    }

    @Test(priority=1)
    public void CheckResponse() {
        Response getResponse = get("https://www.onetwotrip.com/_hotels/api/suggestRequest?query=Mos");
        JSONObject Objects = new JSONObject(getResponse.asString());
        JSONArray Result = Objects.getJSONArray("result");
        Assert.assertTrue("Получен пустой Result", Result.length() != 0);
        System.out.println("Получен не пустой Result:");
        System.out.println(Objects);
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i < Result.length(); ++i) {
            JSONObject res = Result.getJSONObject(i);
            String country = res.getString("country");
            String name = res.getString("name");
            String type = res.getString("type");
            int id = res.getInt("id");
            if (type.equals("hotel")) ++a;
            if (type.equals("airport")) ++b;
            if (country.equals("Россия") && name.equals("Москва") && id == 524901)
            {System.out.println("Результат " + country + ", город - " + name + ", c id - " + id + " присутсвует в ответе"); ++c;}
            }
        if (a>0) System.out.println("Результат содержит по крайней мере 1 отель");
        if (b>0) System.out.println("Результат содержит по крайней мере 1 аэропорт");
        Assert.assertFalse("Результат не содержит отели", a == 0);
        Assert.assertFalse("Результат не содержит аэропорты", b == 0);
        Assert.assertFalse("Результат не содержит ключевой записи Россия, город - Москва, id - 524901", c == 0);
        }
    }

