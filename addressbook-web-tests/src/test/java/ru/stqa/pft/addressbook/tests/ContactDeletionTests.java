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

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() throws IOException {
        if (app.db().contacts().size() == 0) {
            logger.info("Упс...контактов нет, не беда - создадим");
            Groups groups = app.db().groups();
            Properties properties = new Properties();
            String target = System.getProperty("target", "local");
            properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
            app.contact().create(new ContactData().withFirstname(properties.getProperty("contact.name"))
                    .withLastname(properties.getProperty("contact.lastName")).inGroup(groups.iterator().next()));
            logger.info("Контакт создан");
        }
        logger.info("Идём на стартовую страницу");
        app.goTo().homePage();
    }

    @Test
    public void testContactDeletion() {
        logger.info("Считаем контакты до удаления");
        Contacts before = app.db().contacts();
        logger.info("Выберем удаляемый контакт");
        ContactData deletedContact = before.iterator().next();
        logger.info("Удалим контакт");
        app.contact().delete(deletedContact);
        logger.info("Убедимся, что количество контактов уменьшилось на 1");
        assertThat(app.contact().Count(), equalTo(before.size() - 1));
        logger.info("Считаем контакты после удаления");
        Contacts after = app.db().contacts();
        logger.info("Убедимся, что удалился нужный контакт");
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();
    }

}
