package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private final String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

        switch (browser) {
            case BrowserType.CHROME:
                wd = new ChromeDriver();
                break;
            case BrowserType.FIREFOX:
                wd = new FirefoxDriver();
                break;
            case BrowserType.IE:
                wd = new InternetExplorerDriver();
                break;
            case BrowserType.EDGE:
                wd = new EdgeDriver();
                break;
            case BrowserType.OPERA:
                wd = new OperaDriver();
                break;
        }
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
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
