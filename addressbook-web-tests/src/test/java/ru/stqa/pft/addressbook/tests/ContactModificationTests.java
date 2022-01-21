package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() throws IOException {
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            Properties properties = new Properties();
            String target = System.getProperty("target", "local");
            properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
            app.contact().create(new ContactData().withFirstname(properties.getProperty("contact.name"))
                    .withLastname(properties.getProperty("contact.lastName")).inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactModification() throws IOException {
        Properties properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname(properties.getProperty("contactrename.name"))
                .withLastname(properties.getProperty("contactrename.lastName"));
        app.contact().modify(contact);
        assertThat(app.contact().Count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

}

