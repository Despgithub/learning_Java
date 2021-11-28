package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String homephone;
    private final String mobile;
    private final String email;
    private final String homepage;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String title,
                       String company, String address, String homephone, String mobile, String email, String homepage) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homephone = homephone;
        this.mobile = mobile;
        this.email = email;
        this.homepage = homepage;
    }

    public String getFirstname() {
        return firstname;
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

    public String getHomephone() {
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
}
