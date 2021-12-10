package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        int before = app.getContactHelper().getConactCount();
        app.getContactHelper().gotoContactCreationPage();
        app.getContactHelper().createContact(new ContactData("Ostap", "Suleiman Berta Maria", "Bender",
                "0$ia", "The great combinator", "Horns and hooves", "Russia,Moscow, " +
                "Old Arbat street 13,1", null, "+79111111111", "0$ia@bender.ru", "[none]"
                , "www.horns&hooves.com"));
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getConactCount();
        Assert.assertEquals(after, before + 1);
        app.getSessionHelper().logout();
    }
}
