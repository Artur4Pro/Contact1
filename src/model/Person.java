package model;

import model.enumTypes.EmailType;
import model.enumTypes.PhoneNumberType;

public class Person {

    private String name;
    private String phoneNumber;
    private PhoneNumberType phoneNumberType;
    private String eMail;
    private EmailType emailType;

    public Person() {
    }

    public Person(String name, String phoneNumber, PhoneNumberType phoneNumberType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
    }

    public Person(String name, String phoneNumber, PhoneNumberType phoneNumberType, String eMail, EmailType emailType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
        this.eMail = eMail;
        this.emailType = emailType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    public void setPhoneNumberType(PhoneNumberType phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

    @Override
    public String toString() {
        if (eMail == null) {
            return
                    "Name  " + name +
                            " :  Phone Number  " + phoneNumber +
                            " :  Group  " + phoneNumberType +
                            " :";
        }
        return
                "Name  " + name +
                        " :  Phone Number  " + phoneNumber +
                        " :  Group  " + phoneNumberType +
                        " :  Email  " + emailType + " : " + eMail ;
    }
}

