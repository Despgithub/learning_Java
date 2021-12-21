package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ostap").withLastname("Bender")
                    .withHomePhone("111111").withMobile("222222").withWorkPhone("333333"));
        }
        app.goTo().homePage();
    }

    @Test(enabled = true, invocationCount = 1)
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(cleared(contactInfoFormEditForm.getHomePhone())));
        assertThat(contact.getMobile(), equalTo(cleared(contactInfoFormEditForm.getMobile())));
        assertThat(contact.getWorkPhone(), equalTo(cleared(contactInfoFormEditForm.getWorkPhone())));
    }

    public String cleared(String phone){
        return phone.replaceAll("[-()\\s]","");
    }

}
