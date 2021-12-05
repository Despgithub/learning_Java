package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactCreationPage();
        app.getContactHelper().fillContactForm(new ContactData("Ostap", "Suleiman Berta Maria", "Bender",
                "0$ia", "The great combinator", "Horns and hooves", "Russia,Moscow, " +
                "Old Arbat street 13,1", null, "+79111111111", "0$ia@bender.ru", "Test1"
                , "www.horns&hooves.com"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        app.getSessionHelper().logout();
    }
}
