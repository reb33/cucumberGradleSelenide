package steps;

import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Selenide.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.FirstPage;
import pages.bloks.Duck;

import java.util.List;

/**
 * Created by konstantin on 30.01.2017.
 */
public class MySteps {

    FirstPage firstPage = page(new FirstPage());

    @Given("приложение запущено")
    public void start(){
        open("https://demo.litecart.net/en/");
    }

    @Then("данные контейнера ")
    public void getContainer(){

    }

    @Then("данные коллекции")
    public void getCollection(){
        List<Duck> ducks = firstPage.getCollection("популярные утки");
        ducks.get(0).get("название");
        System.out.println();
    }
}
