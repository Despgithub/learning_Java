package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Ippolit", "Matveevich", "Vorobyaninov",
                "Kitty", "marshal of the nobility", "Department of finance", "Russia,Moscow, " +
                "Old Arbat street 1,5", "4951111111", "+792222222", "IppolitMatveevich@gold.ru",null
                ,"www.gold.com"),false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        app.getSessionHelper().logout();
    }
}
