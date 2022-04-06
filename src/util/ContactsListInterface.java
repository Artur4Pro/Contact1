package util;

import model.User;
import model.enumTypes.EmailType;
import model.enumTypes.PhoneNumberType;

import java.util.List;

public interface ContactsListInterface {
    void prepareNewUser();

    void printUsers();

    void editContact();

    void deleteContact();

    void search();

    void searchTypeTaker();

    void searchFromNumberType();

    void searchFromEmail();

    void searchFromEmailType();

    void searchFromNumber();

    void searchFromName();

    PhoneNumberType phoneNumberTypeTaker();

    EmailType eMailTypeTaker();

    void createOrNote(String name, String phoneNumber, PhoneNumberType phoneNumberType,
                      String eMail, EmailType emailType, boolean haveEmail);

    EmailType emailTypeChecker(String eMail);

    User userFinderByPhoneNumber(List<User> list, String phoneNumber);

    List<User> getUsersList();

    void exit();
}
