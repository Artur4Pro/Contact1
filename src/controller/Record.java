package controller;

import model.ContactsList;
import model.Person;
import model.User;
import model.enumTypes.EmailType;
import model.enumTypes.PhoneNumberType;

import java.io.*;



public class Record {

    public static void writeRecord(ContactsList contactsList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Contacts.txt"));
            for (User user : contactsList.getUsersList()) {
                if (user.getPerson().geteMail() != null) {
                    writer.write(user.getPerson().getName() + "¤" + user.getPerson().getPhoneNumber() + "¤" +
                            user.getPerson().getPhoneNumberType() + "¤" + user.getPerson().geteMail() + "¤" + user.getPerson().getEmailType() + "\n");
                } else {
                    writer.write(user.getPerson().getName() + "¤" + user.getPerson().getPhoneNumber() + "¤" + user.getPerson().getPhoneNumberType() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readRecord(ContactsList contacts) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Contacts.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                User user = new User(new Person());
                arr = element.split("¤");
                if (arr.length == 3) {
                    setNumberNameNumberTypeFromRecord(arr, contacts, user);
                } else if (arr.length == 5) {
                    setEmailEmailTypeFromRecord(arr, contacts, user);
                }
                contacts.getUsersList().add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static PhoneNumberType getPhoneTypeFromRecord(String[] arr) {
        return switch (arr[2]) {
            case "MOBILE" -> PhoneNumberType.MOBILE;
            case "HOME" -> PhoneNumberType.HOME;
            case "WORK" -> PhoneNumberType.WORK;
            case "SCHOOL" -> PhoneNumberType.SCHOOL;
            case "COMPANY" -> PhoneNumberType.COMPANY;
            default -> null;
        };
    }

    private static EmailType getEmailTypeFromRecord(String[] arr) {
        return switch (arr[4]) {
            case "MAIL" -> EmailType.MAIL;
            case "YAHOO" -> EmailType.YAHOO;
            case "YANDEX" -> EmailType.YANDEX;
            case "ACA" -> EmailType.ACA;
            case "GMAIL" -> EmailType.GMAIL;
            case "ICLOUD" -> EmailType.ICLOUD;
            case "OTHER" -> EmailType.OTHER;
            default -> null;
        };
    }

    private static void setNumberNameNumberTypeFromRecord(String[] arr, ContactsList contacts, User user) {
        user.getPerson().setName(arr[0]);
        user.getPerson().setPhoneNumber(arr[1]);
        user.getPerson().setPhoneNumberType(getPhoneTypeFromRecord(arr));
        User.phoneNumberAndNameSetter(arr[1], arr[0]);
        User.phoneNumberTypeAndPhoneNumberSetter(arr[1], getPhoneTypeFromRecord(arr));
    }

    private static void setEmailEmailTypeFromRecord(String[] arr, ContactsList contacts, User user) {
        setNumberNameNumberTypeFromRecord(arr, contacts, user);
        user.getPerson().seteMail(arr[3]);
        user.getPerson().setEmailType(emailTypeChecker(arr[3]));
        User.emailAndNameSetter(user.getPerson().getName(), arr[3]);
        User.emailAndEmailTypeSetter(arr[3], emailTypeChecker(arr[3]));
    }
    public static   EmailType emailTypeChecker(String eMail) {
        return EmailType.getMailType(cuttingMailForCheckingType(eMail));
    }
    public static String cuttingMailForCheckingType(String eMail) {
        int substringStart = 0;
        for (int i = 0; i < eMail.length(); i++) {
            if (eMail.charAt(i) == '@') {
                substringStart = i;
            }
        }
        return eMail.substring(substringStart);
    }
}
