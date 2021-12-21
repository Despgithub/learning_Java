package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ostap").withLastname("Bender").withGroup(1));
        }
        app.goTo().homePage();
    }

    @Test(enabled = true, invocationCount = 1)
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.contact().createContactPage();
        ContactData contact = new ContactData().withFirstname("Ostap").withLastname("Bender").withGroup(1);
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().Count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test(enabled = true, invocationCount = 1)
    public void testBadContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.contact().createContactPage();
        ContactData contact = new ContactData().withFirstname("Ostap'").withLastname("Bender").withGroup(1);
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().Count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}
