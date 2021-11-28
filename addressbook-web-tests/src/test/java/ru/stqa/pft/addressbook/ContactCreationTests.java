package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        gotoContactCreationPage();
        fillContactForm(new ContactData("Ostap", "Suleiman Berta Maria", "Bender",
                "0$ia", "The great combinator", "Horns and hooves", "Russia,Moscow, " +
                "Old Arbat street 13,1", "4950000000", "+79111111111", "0$ia@bender.ru",
                "www.horns&hooves.com"));
        submitContactCreation();
        returnToHomePage();
        logout();
    }
}
