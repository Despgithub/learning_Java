package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("Ostap", "Suleiman Berta Maria", "Bender",
                    "0$ia", "The great combinator", "Horns and hooves", "Russia,Moscow, " +
                    "Old Arbat street 13,1", null, "+79111111111", "0$ia@bender.ru", 1
                    , "www.horns&hooves.com"));
        }
        app.goTo().homePage();
    }

    @Test(enabled = true, invocationCount = 1)
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
