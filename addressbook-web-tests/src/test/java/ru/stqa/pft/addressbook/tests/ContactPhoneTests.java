package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ostap").withLastname("Bender")
                    .withHomePhone("111111").withMobile("222222").withWorkPhone("333333"));
        }
        app.goTo().homePage();
    }

    @Test(enabled = false, invocationCount = 1)
    public void testContactCreation() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
    }

}
