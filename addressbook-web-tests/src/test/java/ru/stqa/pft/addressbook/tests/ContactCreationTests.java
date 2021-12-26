package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader((new File("src/test/resources/contacts.xml"))));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader((new File("src/test/resources/contacts.json"))));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @BeforeMethod
    public void insurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ostap").withLastname("Bender").withGroup(1));
        }
        app.goTo().homePage();
    }

    @Test(enabled = true, invocationCount = 1, dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.contact().createContactPage();
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().Count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
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
