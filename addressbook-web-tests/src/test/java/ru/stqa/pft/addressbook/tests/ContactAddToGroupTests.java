package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

public class ContactAddToGroupTests extends TestBase {


    @BeforeMethod
    public void insurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Firstname").withLastname("Lastname"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Name").withHeader("Header").withFooter("Footer"));
        }
    }

    @Test
    public void testContactAddToGroup() {
        app.goTo().homePage();
        ContactData addedContact = selectContacts();
        GroupData groupToAdd = selectGroups(addedContact);
        app.contact().addContactInGroup(addedContact, groupToAdd);
    }

    public GroupData selectGroups(ContactData contact) {
        Groups groups = app.db().groups();
        Set<GroupData> freeGroups = new HashSet<>(groups);
        freeGroups.removeAll(contact.getGroups());
        if (freeGroups.size() == 0) {
            app.goTo().groupPage();
            GroupData added = new GroupData();
            app.group().create(added.withName("9_9_9").withHeader("8_8_8").withFooter("7_7_7"));
            app.goTo().homePage();
            freeGroups.add(added);
            Groups reload = app.db().groups();
            Set<GroupData> afterReload = new HashSet<>(reload);
            freeGroups.removeAll(contact.getGroups());
            freeGroups = afterReload;
        }
        return freeGroups.iterator().next();
    }

    public ContactData selectContacts() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size()) {
                return contact;
            }
        }
        return contacts.iterator().next();
    }

}
