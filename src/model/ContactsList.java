package model;

import controller.PersonController;
import controller.Record;
import util.ContactsListInterface;
import validators.Validators;
import model.enumTypes.EmailType;
import model.enumTypes.PhoneNumberType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactsList implements ContactsListInterface {
    PersonController pc = new PersonController();
    private final List<User> usersList = new ArrayList<>();

    @Override
    public void prepareNewUser() {
        String name = pc.addName();

        String phoneNumber;
        do {
            phoneNumber = pc.addPhoneNumber();
        } while (!Validators.isNotContainsPhoneNumber(phoneNumber, User.phoneNumberAndName));

        PhoneNumberType phoneNumberType = phoneNumberTypeTaker();
        String question = pc.mailQuestion();
        boolean haveEmail = false;
        String eMail = null;
        EmailType emailType = null;
        if (question.equalsIgnoreCase("y")) {
            haveEmail = true;
            do {
                eMail = pc.addMail();
                emailType = Record.emailTypeChecker(eMail);
            }
            while (!Validators.isNotContainEmail(eMail, User.emailAndMailType));
        }
        createOrNote(name, phoneNumber, phoneNumberType, eMail, emailType, haveEmail);
    }

    @Override
    public void printUsers() {
        if (Validators.isNotZeroSize(usersList)) {
            for (User user : usersList) {
                System.out.println(user);
            }
        }
    }

    @Override
    public void editContact() {
        if (Validators.isNotZeroSize(usersList)) {
            System.out.print("For finding ");
            String phoneNumber = pc.addPhoneNumber();
            if (Validators.isContainPhoneNumber(usersList, phoneNumber)) {

                User user = userFinderByPhoneNumber(usersList, phoneNumber);

                String name;
                PhoneNumberType phoneNumberType;
                String eMAIL;

                if (pc.nameEditQuestion().equalsIgnoreCase("y")) {
                    name = pc.addName();
                    user.getPerson().setName(name);
                    User.phoneNumberAndName.replace(phoneNumber, name); // mail u anumy che
                    if (user.getPerson().geteMail() != null) {
                        User.emailAndName.replace(user.getPerson().geteMail(), name);
                    }
                }
                if (pc.phoneTypeEditQuestion().equalsIgnoreCase("y")) {
                    phoneNumberType = phoneNumberTypeTaker();
                    user.getPerson().setPhoneNumberType(phoneNumberType);
                    User.phoneNumberTypeAndPhoneNumber.remove(user.getPerson().getPhoneNumberType());
                    User.phoneNumberTypeAndPhoneNumber.put(phoneNumberType, phoneNumber);
                }
                if (pc.eMailEditQuestion().equalsIgnoreCase("y")) {
                    do {
                        eMAIL = pc.addMail();
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
    }

    @Override
    public void deleteContact() {
        if (Validators.isNotZeroSize(usersList)) {
            String phoneNumber = pc.addPhoneNumber();
            if (Validators.isContainPhoneNumber(usersList, phoneNumber)) {

                User user = userFinderByPhoneNumber(usersList, phoneNumber);
                if (pc.deleteQuestion(user).equalsIgnoreCase("y")) {
                    usersList.remove(user);
                    User.phoneNumberAndName.remove(user.getPerson().getPhoneNumber());
                    User.phoneNumberTypeAndPhoneNumber.remove(user.getPerson().getPhoneNumberType());
                    if (user.getPerson().geteMail() != null) {
                        User.emailAndName.remove(user.getPerson().geteMail());
                        User.emailAndMailType.remove(user.getPerson().geteMail());
                    }
                    System.err.println("\n>>>CONTACT DELETED<<<\n");
                }
            }
        }
    }

    @Override
    public void search() {
        if (Validators.isNotZeroSize(usersList)) {
            searchTypeTaker();
        }
    }

    @Override
    public void searchTypeTaker() {

        String search = pc.selectSearchTypeFromMenu();
        switch (search) {
            case "1" -> searchFromName();
            case "2" -> searchFromNumber();
            case "3" -> searchFromNumberType();
            case "4" -> searchFromEmail();
            case "5" -> searchFromEmailType();
        }
    }

    @Override
    public void searchFromNumberType() {
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

    @Override
    public void searchFromEmail() {
        int find = 0;
        System.out.print("For searching ");
        String SearchEMail = pc.addSearchMail();
        for (User user : usersList) {
            if (user.getPerson().geteMail().toLowerCase().contains(SearchEMail)) {
                find++;
                System.out.println(user.toString());
            }

        }
        if (find == 0) {
            System.err.println("\n>>>THERE IS NO SUCH CONTACT<<<\n");
        }
    }

    @Override
    public void searchFromEmailType() {
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

    @Override
    public void searchFromNumber() {
        int find = 0;
        System.out.print("\nFor searching ");
        String searchNumber = pc.addPhoneNumber();
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

    @Override
    public void searchFromName() {
        int find = 0;
        System.out.print("\nFor searching ");
        String searchName = pc.addName();
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

    @Override
    public PhoneNumberType phoneNumberTypeTaker() {
        String phoneNumberType = pc.selectPhoneNumberTypeFromMenu();
        switch (phoneNumberType) {
            case "1" -> {
                return PhoneNumberType.MOBILE;
            }
            case "2" -> {
                return PhoneNumberType.HOME;
            }
            case "3" -> {
                return PhoneNumberType.WORK;
            }
            case "4" -> {
                return PhoneNumberType.SCHOOL;
            }
            case "5" -> {
                return PhoneNumberType.COMPANY;
            }
        }
        return phoneNumberTypeTaker();
    }

    @Override
    public EmailType eMailTypeTaker() {
        String eMailType = pc.selectEmailTypeFromMenu();
        switch (eMailType) {
            case "1" -> {
                return EmailType.MAIL;
            }
            case "2" -> {
                return EmailType.GMAIL;
            }
            case "3" -> {
                return EmailType.ICLOUD;
            }
            case "4" -> {
                return EmailType.YAHOO;
            }
            case "5" -> {
                return EmailType.YANDEX;
            }
            case "6" -> {
                return EmailType.ACA;
            }
            case "7" -> {
                return EmailType.OTHER;
            }
            default -> System.err.println("\nWrong input. Try again.\n");
        }
        return eMailTypeTaker();
    }

    @Override
    public void createOrNote(String name, String phoneNumber, PhoneNumberType phoneNumberType,
                             String eMail, EmailType emailType, boolean haveEmail) {
        String yesOrNo = pc.creatingQuestion();
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

    @Override
    public EmailType emailTypeChecker(String eMail) {
        return EmailType.getMailType(Record.cuttingMailForCheckingType(eMail));
    }

    @Override
    public User userFinderByPhoneNumber(List<User> list, String phoneNumber) {
        int x = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPerson().getPhoneNumber().equals(phoneNumber)) {
                x = i;
            }
        }
        return list.get(x);
    }

    @Override
    public List<User> getUsersList() {
        return usersList;
    }

    @Override
    public void exit() {
        pc.scannerCloser();
    }
}