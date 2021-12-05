package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ostap", "Suleiman Berta Maria", "Bender",
                    "0$ia", "The great combinator", "Horns and hooves", "Russia,Moscow, " +
                    "Old Arbat street 13,1", null, "+79111111111", "0$ia@bender.ru", "[none]"
                    , "www.horns&hooves.com"), true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Ippolit", "Matveevich", "Vorobyaninov",
                "Kitty", "marshal of the nobility", "Department of finance", "Russia,Moscow, " +
                "Old Arbat street 1,5", "4951111111", "+792222222", "IppolitMatveevich@gold.ru", null
                , "www.gold.com"), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        app.getSessionHelper().logout();
    }
}

