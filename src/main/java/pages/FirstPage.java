package pages;

import anatations.NameOfElement;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by konstantin on 30.01.2017.
 */
public class FirstPage {

    @NameOfElement(value = "фронтовая картинка")
    @FindBy(id="rslides1_s0")
    SelenideElement frontPic;

    @NameOfElement(value = "популярная утка")
    @FindBy(css="#box-most-popular ul li")
    ElementsContainer popularDuck;


}
