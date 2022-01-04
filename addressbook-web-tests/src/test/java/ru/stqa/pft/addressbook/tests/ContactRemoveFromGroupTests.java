package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveFromGroupTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Name").withHeader("Header").withFooter("Footer"));
        }
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Firstname").withLastname("Lastname")
                    .inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        app.goTo().homePage();
        ContactData addedContact = selectContacts();
        GroupData groupToAdd = selectGroups(addedContact);
        app.contact().removeContactFromGroup(addedContact, groupToAdd);
    }

    public GroupData selectGroups(ContactData contact) {

        return contact.getGroups().iterator().next();
    }

    public ContactData selectContacts() {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
        return contacts.iterator().next();
    }
}
