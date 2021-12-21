package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String title;
    private String company;
    private String address;
    private String homephone;
    private String mobile;
    private String email;
    private int group;
    private String homepage;
    private String workphone;

    public String getFirstname() {
        return firstname;
    }

    public String getWorkPhone() {
        return workphone;
    }

    public int getId() {
        return id;
    }


    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homephone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getGroup() {
        return group;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withWorkPhone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(int group) {
        this.group = group;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (!Objects.equals(firstname, that.firstname)) return false;
        return Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

}
