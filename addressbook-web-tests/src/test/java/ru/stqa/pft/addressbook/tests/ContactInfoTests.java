package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ostap").withLastname("Bender")
                    .withAddress("Russia,Moscow, Old Arbat street 13, kv 1").withHomePhone("111111")
                    .withMobile("222222").withWorkPhone("333333").withEmail("email@test.ru")
                    .withEmail2("email2@test.ru").withEmail3("email3@test.ru").withSecondPhone("444444"));
        }
        app.goTo().homePage();
    }

    @Test(enabled = true, invocationCount = 1)
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
