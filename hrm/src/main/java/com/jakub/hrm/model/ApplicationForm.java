package com.jakub.hrm.model;

public class ApplicationForm {
    private String firstName;
    private String lastName;
    private String email;
    private String mobilePhone;
    private String aboutYourself;


    public ApplicationForm(String firstName,String lastName, String email, String mobilePhone, String aboutYourself) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.aboutYourself = aboutYourself;
    }


    public String getName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }


    @Override
    public String toString() {
        return "ApplicationForm{" +
                "name='" + firstName + '\'' +
                "lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", aboutYourself='" + aboutYourself + '\'' +
                '}';
    }
}
