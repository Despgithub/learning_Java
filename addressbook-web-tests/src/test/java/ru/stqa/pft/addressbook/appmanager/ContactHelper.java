package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWorkPhone());
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

    public void selectContactById(int id) {
        wd.findElement(By.xpath("//input[@value='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));

    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
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
        contactCashe = null;
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCashe = null;
        returnContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCashe = null;
        closeAlert();
        returnContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//tr[2]//input"));
    }

    public int Count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCashe = null;

    public Contacts all() {
        if (contactCashe != null) {
            return new Contacts(contactCashe);
        }
        contactCashe = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.xpath(".//td"));
            int id = Integer.parseInt(element.findElement(By.xpath(".//input")).getAttribute("value"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            contactCashe.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones));
        }
        return contactCashe;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(homephone).withMobile(mobile).withWorkPhone(workphone);
    }
}
