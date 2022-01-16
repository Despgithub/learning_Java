package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws MessagingException, IOException {
        Users users = app.db().users();
        int size = users.size();
        int item = new Random().nextInt(size);
        int i = 0;
        UserData selectedUser = null;
        for (UserData select : users) {
            if (i == item)
                selectedUser = select;
            i++;
        }
        String username = Objects.requireNonNull(selectedUser).getUsername();
        String email = selectedUser.getEmail();
        String newPassword = ("password2");
        app.registration().loginFromUI(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.registration().resetPassword(username);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(username, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
