package pages;

import anatations.NameOfElement;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by konstantin on 30.01.2017.
 */
public abstract class AbstractPage {


    public SelenideElement get(String elementName){
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            return (SelenideElement) Arrays.asList(fields)
                    .stream()
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get().get(this);
        } catch (IllegalAccessException e) {
            Selenide.screenshot("No_element");
            throw new Error("ERROR: element with name " + elementName + " at page " + this.getClass().getName() + " is not public");
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_element");
            throw new NoSuchElementException("ERROR: there is no such element with name " + elementName + " at page " + this.getClass().getName());
        }
    }

    public ElementsContainer getContainer(String elementName){
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            return (ElementsContainer) Arrays.asList(fields)
                    .stream()
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get().get(this);
        } catch (IllegalAccessException e) {
            Selenide.screenshot("No_container");
            throw new Error("ERROR: container with name " + elementName + " at page " + this.getClass().getName() + " is not public");
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_container");
            throw new NoSuchElementException("ERROR: there is no such container with name " + elementName + " at page " + this.getClass().getName());
        }
    }
}
