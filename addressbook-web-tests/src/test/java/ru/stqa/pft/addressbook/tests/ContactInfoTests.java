package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() throws IOException {
        if (app.contact().all().size() == 0) {
            Properties properties = new Properties();
            String target = System.getProperty("target", "local");
            properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
            app.contact().create(new ContactData().withFirstname(properties.getProperty("contact.name"))
                    .withLastname(properties.getProperty("contact.lastName"))
                    .withGroup(Integer.parseInt(properties.getProperty("contact.group")))
                    .withWorkPhone(properties.getProperty("contact.workPhone"))
                    .withSecondPhone(properties.getProperty("contact.workPhone2"))
                    .withMobile(properties.getProperty("contact.mobile"))
                    .withHomePhone(properties.getProperty("contact.homePhone"))
                    .withAddress(properties.getProperty("contact.address"))
                    .withEmail(properties.getProperty("contact.email"))
                    .withEmail2(properties.getProperty("contact.email2"))
                    .withEmail3(properties.getProperty("contact.email3")));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactInfo() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFormEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFormEditForm)));
        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFormEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone(), contact.getSecondphone()).
                filter((s) -> !s.equals("")).map(ContactInfoTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhones(String phone) {
        return phone.replaceAll("[-()\\s]", "");
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).
                filter((e) -> !e.equals("")).map(ContactInfoTests::cleanedEmails)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedEmails(String email) {
        return email.trim().replaceAll(" +", " ");
    }

    private String mergeAddress(ContactData contact) {
        return contact.getAddress().trim().replaceAll(" +", " ");
    }
}
