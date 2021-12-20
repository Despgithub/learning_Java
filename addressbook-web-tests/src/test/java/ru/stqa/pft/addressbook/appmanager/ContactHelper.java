package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']//input[@value='Enter']"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
        type(By.name("homepage"), contactData.getHomepage());
        if (creation) {
            Select select = new Select(wd.findElement(By.name("new_group")));
            if (select.getOptions().size() > 0 && select.getOptions().size() > contactData.getGroup()) {
                select.selectByIndex(contactData.getGroup());
            } else {
                select.selectByIndex(select.getOptions().size() - 1);
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));

    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void createContactPage() {
        click(By.linkText("add new"));
    }

    public void returnContactPage() {
        click(By.linkText("home"));
    }

    public void create(ContactData contact) {
        createContactPage();
        fillContactForm(contact, true);
        submitContactCreation();
    }


    public void modify(int index, ContactData contact) {
        initContactModification(index);
        fillContactForm(contact, false);
        submitContactModification();
        returnContactPage();
    }


    public void delete(int index) {
        selectContact(index);
        deleteSelectedContacts();
        closeAlert();
        returnContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//tr[2]//input"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.xpath(".//td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null,
                    null, null, null, null, 0, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
