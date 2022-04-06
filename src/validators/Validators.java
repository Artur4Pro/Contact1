package validators;

import model.ContactsList;
import model.User;
import model.enumTypes.EmailType;

import java.util.List;
import java.util.Map;

public class Validators {

    public static boolean isTruePhoneNumber(String phoneNumber) {
        if (phoneNumber.strip().length() == 0 || phoneNumber.substring(1, phoneNumber.length()).contains("+")) {
            System.err.println("\n Number is not correct. Try again.\n");
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if ((phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9') && phoneNumber.charAt(i) != '+' && phoneNumber.charAt(i) != ' ') {
                System.err.println("\nNumber is not correct. Try again.\n");
                return false;
            }
        }
        return true;
    }

    public static boolean isTrueNumberType(String selectedNumber) {
        if (selectedNumber.length() > 1 && (selectedNumber.charAt(0) < '1' || selectedNumber.charAt(0) > '5')) {
            System.err.println("\nYou entered wrong number. Try again.\n");
            return false;
        }
        return true;
    }

    public static boolean isTrueMail(String mail) {
        if(mail.contains(" ")){
            System.err.println("\n Wrong email address. Try again.\n");
            return false;
        }
        boolean flag = false;
        int countOfDog = 0;
        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == '@') {
                countOfDog++;
            }
            if (mail.charAt(i) == '@' && i != 0) {
                flag = true;
            }
            if (countOfDog > 1) {
                System.err.println("\n Wrong email address. Try input again.\n");
                return false;
            }
            if (i + 1 < mail.length() && flag && (mail.charAt(i) == '.')) {
                return true;
            }
        }
        System.err.println("\n Wrong email address. Try again.\n");
        return false;
    }

    public static boolean isTrueMailType(String selectedNumber) {
        if (selectedNumber.length() > 1 && (selectedNumber.charAt(0) < '1' || selectedNumber.charAt(0) > '5')) {
            System.err.println("\nYou entered wrong number. Try again.\n");
            return false;
        }
        return true;
    }

    public static boolean isNotContainsPhoneNumber(String str, Map<String, String> map) {
        for (String s : map.keySet()) {
            if (deleteSpace(str).equals(deleteSpace(s))) {
                System.err.println("\nThe entered PHONE NUMBER already exists. Try again.\n");
                return false;
            }
        }
        return true;
    }

    public static boolean isNotContainEmail(String eMail, Map<String, EmailType> map) {
        for (String s : map.keySet()) {
            if (s.equals(eMail)) {
                System.err.println("\nThe entered EMAIL already exists. Try again.\n");
                return false;
            }
        }
        return true;
    }

    public static boolean isContainPhoneNumber(List<User> list, String phoneNumber) {
        for (User user : list) {
            if (user.getPerson().getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        System.err.println("\nContact is not found\n");
        return false;
    }

    public static boolean isNotZeroSize(List<User> usersList) {
        if (usersList.size() == 0) {
            System.err.println("""

                    >>>CONTACTS LIST IS EMPTY<<<
                    >>>FOR ADDING PRESS 1<<<
                    """);
            return false;
        }
        return true;
    }

    public static String deleteSpace(String str) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                ret.append(str.charAt(i));
            }
        }
        return ret.toString();
    }
}
