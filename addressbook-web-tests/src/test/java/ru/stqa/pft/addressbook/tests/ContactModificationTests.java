package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ostap", "Suleiman Berta Maria", "Bender",
                    "0$ia", "The great combinator", "Horns and hooves", "Russia,Moscow, " +
                    "Old Arbat street 13,1", null, "+79111111111", "0$ia@bender.ru", 1
                    , "www.horns&hooves.com"));
        }
        app.goTo().gotoHomePage();
    }

    @Test(enabled = true, invocationCount = 1)
    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "Ippolit", "Matveevich", "Vorobyaninov",
                "Kitty", "marshal of the nobility", "Department of finance", "Russia,Moscow, " +
                "Old Arbat street 1,5", "4951111111", "+792222222", "IppolitMatveevich@gold.ru", 1
                , "www.gold.com");
        app.getContactHelper().modifyContact(index, contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

