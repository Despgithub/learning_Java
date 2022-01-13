package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.xpath("//input[@type='submit']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button[@type='submit']"));
    }

    public void resetPassword(String username) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        click(By.linkText(username));
        click(By.xpath("//input[@value='Сбросить пароль']"));
    }

    public void loginFromUI(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        click(By.xpath("//input[@type='submit']"));
        type(By.name("username"), username);
        click(By.xpath("//input[@type='submit']"));
        type(By.name("password"), password);
        click(By.xpath("//input[@type='submit']"));
    }

}
