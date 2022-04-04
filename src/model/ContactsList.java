package model;

import Validators.Validators;
import controller.InputMethods;
import model.enumTypes.EmailType;
import model.enumTypes.PhoneNumberType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactsList extends InputMethods {
    private final List<User> usersList = new ArrayList<>();

    public void prepareNewUser() {
        String name = addName();

        String phoneNumber;
        do {
            phoneNumber = addPhoneNumber();
        } while (!Validators.isNotContainsPhoneNumber(phoneNumber, User.phoneNumberAndName));

        PhoneNumberType phoneNumberType = phoneNumberTypeTaker();
        String question = mailQuestion();
        boolean haveEmail = false;
        String eMail = null;
        EmailType emailType = null;
        if (question.equalsIgnoreCase("y")) {
            haveEmail = true;
            do {
                eMail = addMail();
            }
            while (!Validators.isNotContainEmail(eMail, User.emailAndMailType));
        }
        emailType=emailTypeChecker(eMail);
        createOrNote(name, phoneNumber, phoneNumberType, eMail, emailType, haveEmail);
    }

    public void printUsers() {
        for (User user : usersList) {
            System.out.println(user);
        }
    }

    public void editContact() {
        System.out.print("For finding ");
        String phoneNumber = addPhoneNumber();
        if (Validators.isContainPhoneNumber(usersList, phoneNumber)) {

            User user = userFinderByPhoneNumber(usersList, phoneNumber);

            String name;
            PhoneNumberType phoneNumberType;
            String eMAIL;

            if (nameEditQuestion().equalsIgnoreCase("y")) {
                name = addName();
                user.getPerson().setName(name);
                User.phoneNumberAndName.replace(phoneNumber, name); // mail u anumy che
                if (user.getPerson().geteMail() != null) {
                    User.emailAndName.replace(user.getPerson().geteMail(), name);
                }
            }
            if (phoneTypeEditQuestion().equalsIgnoreCase("y")) {
                phoneNumberType = phoneNumberTypeTaker();
                user.getPerson().setPhoneNumberType(phoneNumberType);
                User.phoneNumberTypeAndPhoneNumber.remove(user.getPerson().getPhoneNumberType());
                User.phoneNumberTypeAndPhoneNumber.put(phoneNumberType, phoneNumber);
            }
            if (eMailEditQuestion().equalsIgnoreCase("y")) {
                do {
                    eMAIL = addMail();
                }
                while (!Validators.isNotContainEmail(eMAIL, User.emailAndMailType));
                user.getPerson().seteMail(eMAIL);
                EmailType emailType = emailTypeChecker(eMAIL);
                user.getPerson().setEmailType(emailType);
                User.emailAndMailType.remove(user.getPerson().geteMail());
                User.emailAndMailType.put(eMAIL, emailType);
                User.emailAndName.remove(user.getPerson().geteMail());
                User.emailAndName.put(eMAIL, user.getPerson().getName());
            }
        }
    }

    public void deleteContact() {
        String phoneNumber = addPhoneNumber();
        if (Validators.isContainPhoneNumber(usersList, phoneNumber)) {

            User user = userFinderByPhoneNumber(usersList, phoneNumber);
            if (deleteQuestion(user).equalsIgnoreCase("y")) {
                usersList.remove(user);
                User.phoneNumberAndName.remove(user.getPerson().getPhoneNumber());
                User.phoneNumberTypeAndPhoneNumber.remove(user.getPerson().getPhoneNumberType());
                User.emailAndName.remove(user.getPerson().geteMail());
                User.emailAndMailType.remove(user.getPerson().geteMail());

                System.out.println("\n>>>CONTACT DELETED<<<\n");
            }
        }
    }

    public void search() {
        searchTypeTaker();
    }


    private void searchTypeTaker() {

        int search = selectSearchTypeFromMenu();
        switch (search) {
            case 1 -> searchFromName();
            case 2 -> searchFromNumber();
            case 3 -> searchFromNumberType();
            case 4 -> searchFromEmail();
            case 5 -> searchFromEmailType();
        }
    }

    private void searchFromNumberType() {
        System.out.print("\nFor searching ");
        PhoneNumberType phoneType = phoneNumberTypeTaker();
        int find = 0;
        for (User user : usersList) {
            if (user.getPerson().getPhoneNumberType() == phoneType) {
                find++;
                System.out.println(user.toString());
            }
        }
        if (find == 0) {
            System.err.println("\n>>>THERE IS NO SUCH CONTACT<<<\n");
        }
    }

    private void searchFromEmail() {
        int find = 0;
        System.out.print("\nFor searching ");
        String SearchEMail = addMail();
        for (User user : usersList) {
            if (user.getPerson().geteMail() != null) {
                if (user.getPerson().geteMail().equalsIgnoreCase(SearchEMail) ||
                        user.getPerson().geteMail().toLowerCase(Locale.ROOT).contains(SearchEMail.toLowerCase(Locale.ROOT))) {
                    find++;
                    System.out.println(user.toString());
                }
            }
        }
        if (find == 0) {
            System.err.println("\n>>>THERE IS NO SUCH CONTACT<<<\n");
        }
    }

    private void searchFromEmailType() {
        System.out.print("\nFor searching ");
        int find = 0;
        EmailType emailType = eMailTypeTaker();
        for (User user : usersList) {
            if (user.getPerson().getEmailType() == emailType) {
                find++;
                System.out.println(user.toString());
            }
        }
        if (find == 0) {
            System.err.println("\n>>>THERE IS NO SUCH CONTACT<<<\n");
        }
    }

    private void searchFromNumber() {
        int find = 0;
        System.out.print("\nFor searching ");
        String searchNumber = addPhoneNumber();
        for (User user : usersList) {
            if (user.getPerson().getPhoneNumber().equals(searchNumber) ||
                    user.getPerson().getPhoneNumber().contains(searchNumber)) {
                find++;
                System.out.println(user.toString());
            }
        }
        if (find == 0) {
            System.err.println("\n>>>THERE IS NO SUCH CONTACT<<<\n");
        }
    }

    private void searchFromName() {
        int find = 0;
        System.out.print("\nFor searching ");
        String searchName = addName();
        for (User user : usersList) {
            if (user.getPerson().getName().equalsIgnoreCase(searchName) ||
                    user.getPerson().getName().toLowerCase(Locale.ROOT).contains(searchName.toLowerCase(Locale.ROOT))) {
                find++;
                System.out.println(user.toString());
            }
        }
        if (find == 0) {
            System.err.println("\n>>>THERE IS NO SUCH CONTACT<<<\n");
        }
    }


    private PhoneNumberType phoneNumberTypeTaker() {
        int phoneNumberType = selectPhoneNumberTypeFromMenu();
        switch (phoneNumberType) {
            case 1 -> {
                return PhoneNumberType.MOBILE;
            }
            case 2 -> {
                return PhoneNumberType.HOME;
            }
            case 3 -> {
                return PhoneNumberType.WORK;
            }
            case 4 -> {
                return PhoneNumberType.SCHOOL;
            }
            case 5 -> {
                return PhoneNumberType.COMPANY;
            }
        }
        return phoneNumberTypeTaker();
    }

    private EmailType eMailTypeTaker() {
        int eMailType = selectEmailTypeFromMenu();
        switch (eMailType) {
            case 1 -> {
                return EmailType.MAIL;
            }
            case 2 -> {
                return EmailType.GMAIL;
            }
            case 3 -> {
                return EmailType.ICLOUD;
            }
            case 4 -> {
                return EmailType.YAHOO;
            }
            case 5 -> {
                return EmailType.YANDEX;
            }
            case 6 -> {
                return EmailType.ACA;
            }
            case 7 -> {
                return EmailType.OTHER;
            }
            default -> System.err.println("\nWrong input. Try again.\n");
        }
        return eMailTypeTaker();
    }

    private void createOrNote(String name, String phoneNumber, PhoneNumberType phoneNumberType,
                              String eMail, EmailType emailType, boolean haveEmail) {
        String yesOrNo = creatingQuestion();
        if (yesOrNo.equalsIgnoreCase("y")) {
            if (haveEmail) {
                usersList.add(new User(new Person(name, phoneNumber, phoneNumberType, eMail, emailType)));
                User.phoneNumberAndNameSetter(phoneNumber, name);
                User.phoneNumberTypeAndPhoneNumberSetter(phoneNumber, phoneNumberType);
                User.emailAndNameSetter(name, eMail);
                User.emailAndEmailTypeSetter(eMail, emailType);
                System.err.println("\n>>>CONTACT ADDED<<<\n");
            } else {
                usersList.add(new User(new Person(name, phoneNumber, phoneNumberType)));
                User.phoneNumberAndNameSetter(phoneNumber, name);
                User.phoneNumberTypeAndPhoneNumberSetter(phoneNumber, phoneNumberType);
                System.err.println("\n>>>CONTACT ADDED<<<\n");

            }
        }
    }

    private static String cuttingMailForCheckingType(String eMail) {
        int substringStart = 0;
        for (int i = 0; i < eMail.length(); i++) {
            if (eMail.charAt(i) == '@') {
                substringStart = i;
            }
        }
        return eMail.substring(substringStart);
    }

    public static EmailType emailTypeChecker(String eMail) {
        return EmailType.getMailType(cuttingMailForCheckingType(eMail));
    }

    private User userFinderByPhoneNumber(List<User> list, String phoneNumber) {
        int x = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPerson().getPhoneNumber().equals(phoneNumber)) {
                x = i;
            }
        }
        return list.get(x);
    }


    public List<User> getUsersList() {
        return usersList;
    }

    public void exit() {
        scannerCloser();
    }
}