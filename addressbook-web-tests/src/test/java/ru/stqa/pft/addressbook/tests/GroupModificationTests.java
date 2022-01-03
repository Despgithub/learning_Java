package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            Properties properties = new Properties();
            String target = System.getProperty("target", "local");
            properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
            app.group().create(new GroupData().withName(properties.getProperty("group.name"))
                    .withHeader(properties.getProperty("group.header"))
                    .withFooter(properties.getProperty("group.footer")));
        }
    }

    @Test
    public void GroupModification() throws IOException {
        Properties properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName(properties.getProperty("grouprename.name"))
                .withHeader(properties.getProperty("grouprename.header"))
                .withFooter(properties.getProperty("grouprename.footer"));
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }

}

