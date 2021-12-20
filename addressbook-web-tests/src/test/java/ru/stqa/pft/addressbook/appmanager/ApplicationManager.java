package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.Browser;

import java.time.Duration;

public class ApplicationManager {
    WebDriver wd;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private final Browser browser;

    public ApplicationManager(Browser browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(Browser.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(Browser.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(Browser.IE)) {
            wd = new InternetExplorerDriver();
        } else if (browser.equals(Browser.EDGE)) {
            wd = new EdgeDriver();
        } else if (browser.equals(Browser.OPERA)) {
            wd = new OperaDriver();
        }
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper session() {
        return sessionHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
}
