package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() throws IOException {
        if (app.db().contacts().size() == 0) {
            logger.info("Упс...контактов нет, не беда - создадим");
            Properties properties = new Properties();
            String target = System.getProperty("target", "local");
            properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
            app.contact().create(new ContactData().withFirstname(properties.getProperty("contact.name"))
                    .withLastname(properties.getProperty("contact.lastName"))
                    .withGroup(Integer.parseInt(properties.getProperty("contact.group"))));
        }
        logger.info("Идём на стартовую страницу");
        app.goTo().homePage();
    }

    @Test
    public void testContactModification() throws IOException {
        Properties properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
        logger.info("Считаем контакты до изменения");
        Contacts before = app.db().contacts();
        logger.info("Выберем изменяемый контакт");
        ContactData modifiedContact = before.iterator().next();
        logger.info("Изменим контакт");
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname(properties.getProperty("contactrename.name"))
                .withLastname(properties.getProperty("contactrename.lastName"));
        app.contact().modify(contact);
        logger.info("Убедимся, что количество контактов не изменилось");
        assertThat(app.contact().Count(), equalTo(before.size()));
        logger.info("Считаем контакты после изменения");
        Contacts after = app.db().contacts();
        logger.info("Убедимся что изменения прошли корректно");
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

}

