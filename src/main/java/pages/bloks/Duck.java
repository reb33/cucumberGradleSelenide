package pages.bloks;

import anatations.NameOfElement;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by konstantin on 30.01.2017.
 */
public class Duck extends ElementsContainer{

    @NameOfElement(value = "название")
    @FindBy(css=".name")
    public SelenideElement name;

    @NameOfElement(value = "производство")
    @FindBy(css=".manufacturer")
    public SelenideElement manufacturer;

    @NameOfElement(value = "цена")
    @FindBy(css=".price")
    public SelenideElement price;


}
