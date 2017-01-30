package steps;

import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Selenide.*;
import cucumber.api.java.en.Given;
import pages.FirstPage;

/**
 * Created by konstantin on 30.01.2017.
 */
public class MySteps {

    FirstPage firstPage = page(new FirstPage());

    @Given("Запустить")
    public void start(){
        open("https://demo.litecart.net/en/");
    }
}
