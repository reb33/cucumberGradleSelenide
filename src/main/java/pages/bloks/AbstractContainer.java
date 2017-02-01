package pages.bloks;

import anatations.NameOfBlock;
import anatations.NameOfElement;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.SelenideElementListProxy;
import com.codeborne.selenide.impl.SelenideFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by konstantin on 31.01.2017.
 */
public class AbstractContainer extends ElementsContainer {

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

    public List<ElementsContainer> getCollection(String elementName){
        Field[] fields = this.getClass().getDeclaredFields();
        Field returnField;
        try {
            returnField = Arrays.asList(fields)
                    .stream()
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get();
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_container");
            throw new NoSuchElementException("ERROR: there is no such container with name " + elementName + " at page " + this.getClass().getName());
        }

        List<SelenideElement> listElements;
        try {
            listElements= (List<SelenideElement>) returnField.get(this);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }

        Class<?> listType = returnField.getAnnotation(NameOfBlock.class).value();
        List<ElementsContainer> result = new ArrayList<>();
        listElements.stream().forEach(element -> result.add(createContainer(listType, element)));

        return result;
    }

    private ElementsContainer createContainer (Class<?> listType, SelenideElement self){
        Constructor<?> constructor = null;
        try {
            constructor = listType.getDeclaredConstructor();
            constructor.setAccessible(true);
            ElementsContainer result = (ElementsContainer) constructor.newInstance();
            PageFactory.initElements(new SelenideFieldDecorator(self), result);
            result.setSelf(self);
            return result;
        } catch (NoSuchMethodException|IllegalAccessException|InstantiationException|InvocationTargetException e) {
            throw new Error(e);
        }
    }

    public List<SelenideElement> getList(String elementName){
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            return (List<SelenideElement>) Arrays.asList(fields)
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
