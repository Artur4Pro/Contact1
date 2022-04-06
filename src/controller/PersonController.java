package controller;

import util.PersonControllerInterface;
import validators.Validators;
import model.User;

import java.util.Scanner;

public class PersonController implements PersonControllerInterface {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String addName() {
        System.out.println("Enter name");
        return scanner.nextLine();
    }

    @Override
    public String addMail() {
        System.out.println("Enter mail");
        String mail = scanner.nextLine();
        if (!Validators.isTrueMail(mail)) {
            return addMail();
        }
        return mail;
    }

    @Override
    public String addSearchMail() {
        System.out.println("Enter mail");
        String mail = scanner.nextLine();
        mail = Validators.deleteSpace(mail).toLowerCase();
        return mail;
    }

    @Override
    public String addPhoneNumber() {
        System.out.println("Enter phone number");
        String phoneNumber = scanner.nextLine();
        if (!Validators.isTruePhoneNumber(phoneNumber)) {
            return addPhoneNumber();
        }
        return Validators.deleteSpace(phoneNumber);
    }

    @Override
    public String selectPhoneNumberTypeFromMenu() {
        String selectedNumber;
        do {
            System.out.println("""
                    Enter type of contact (select number of type from MENU)\s
                     1 -> Mobile ☎\s
                     2 -> Home 🏠\s
                     3 -> Work 👔\s
                     4 -> School 🏫
                     5 -> Company 🛄""");

             selectedNumber = scanner.nextLine();
        }while (!Validators.isTrueNumberType(selectedNumber));
        return selectedNumber;
    }

    @Override
    public String selectEmailTypeFromMenu() {
        String selectedNumber;
        do {
            System.out.println("""
                    Enter type of contact (select number of type from MENU)\s
                     1 -> Mail \s
                     2 -> Gmail\s
                     3 -> iCloud\s
                     4 -> Yahoo\s
                     5 -> Yandex\s
                     6 -> Aca\s
                     7 -> Other""");

             selectedNumber = scanner.nextLine();
        }while (!Validators.isTrueMailType(selectedNumber));
        return selectedNumber;
    }

    @Override
    public String selectSearchTypeFromMenu() {
        String selectedNumber;
        do {
            System.out.println("""
                    Enter type of search (select SEARCH type from MENU)\s
                     1 -> Name 🔍\s
                     2 -> Phone number 🔍\s
                     3 -> Phone number type 🔍\s
                     4 -> Email 🔍\s
                     5 -> Email type 🔍""");

            selectedNumber = scanner.nextLine();
        }while (!Validators.isTrueNumberType(selectedNumber));
        return selectedNumber;
    }

    @Override
    public String mailQuestion() {
        System.out.println("""
                Has he/she email ?
                                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.nextLine();
    }

    @Override
    public String creatingQuestion() {
        System.out.println("""
                Do you want to create(SAVE) this contact ?
                                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.nextLine();
    }

    @Override
    public String nameEditQuestion() {
        System.out.println("""
                Do you want to EDIT NAME ?
                                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.nextLine();
    }

    @Override
    public String phoneTypeEditQuestion() {
        System.out.println("""
                Do you want to EDIT PHONE TYPE ?
                                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.nextLine();
    }

    @Override
    public String eMailEditQuestion() {
        System.out.println("""
                Do you want to EDIT eMail ?
                                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.nextLine();
    }

    @Override
    public String deleteQuestion(User user) {
        System.out.println(user.toString());
        System.out.println("""
                Do you want to DELETE this contact ?
                                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.nextLine();
    }

    @Override
    public void scannerCloser() {
        scanner.close();
    }

}
