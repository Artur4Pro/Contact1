package controller;

import Validators.Validators;
import model.User;
import java.util.Scanner;

public class InputMethods {
    Scanner scanner = new Scanner(System.in);

    protected String addName() {
        System.out.println("Enter name\n");
        String name = scanner.next();

        return Validators.deleteSpace(name);
    }

    protected String addMail() {
        System.out.println("Enter mail\n");
        String mail = scanner.next();
        if (!Validators.isTrueMail(mail)) {
            return addMail();
        }
        return mail;
    }

    protected String addPhoneNumber() {
        System.out.println("Enter phone number\n");
        String phoneNumber = scanner.nextLine();
        if (!Validators.isTruePhoneNumber(phoneNumber)) {
            return addPhoneNumber();
        }
        return phoneNumber;
    }



    protected int selectPhoneNumberTypeFromMenu() {
        System.out.println("""
                Enter type of contact (select number of type from MENU)\s
                 1 -> Mobile\s
                 2 -> Home\s
                 3 -> Work\s
                 4 -> School
                 5 -> Company""");

        int selectedNumber = scanner.nextInt();
        if (!Validators.isTrueNumberType(selectedNumber)) {
            return selectPhoneNumberTypeFromMenu();
        }
        return selectedNumber;
    }

    protected int selectEmailTypeFromMenu (){
        System.out.println("""
                Enter type of contact (select number of type from MENU)\s
                 1 -> Mail\s
                 2 -> Gmail\s
                 3 -> iCloud\s
                 4 -> Yahoo\s
                 5 -> Yandex\s
                 6 -> Aca\s
                 7 -> Other""");

        int selectedNumber= scanner.nextInt();
        if (!Validators.isTrueMailType(selectedNumber)) {
            return selectPhoneNumberTypeFromMenu();
        }
        return selectedNumber;
    }

    protected int selectSearchTypeFromMenu() {
        System.out.println("""
                Enter type of search (select SEARCH type from MENU)\s
                 1 -> Name\s
                 2 -> Phone number\s
                 3 -> Phone number type\s
                 4 -> Email\s
                 5 -> Email type""");

        int selectedNumber = scanner.nextInt();
        if (!Validators.isTrueNumberType(selectedNumber)) {
            return selectSearchTypeFromMenu();
        }
        return selectedNumber;
    }




    protected String mailQuestion() {
        System.out.println("""
                Has he/she email ?
                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.next();
    }

    protected String creatingQuestion (){
        System.out.println("""
                Do you want to create(SAVE) this contact ?
                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.next();
    }

    protected String nameEditQuestion (){
        System.out.println("""
                Do you want to EDIT NAME ?
                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.next();
    }

    protected String phoneTypeEditQuestion (){
        System.out.println("""
                Do you want to EDIT PHONE TYPE ?
                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.next();
    }

    protected String eMailEditQuestion (){
        System.out.println("""
                Do you want to EDIT eMail ?
                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.next();
    }

    protected String deleteQuestion(User user){
        System.out.println(user.toString());
        System.out.println("""
                Do you want to DELETE this contact ?
                
                If YES -> enter 'Y'
                If NO -> enter anything else""");

        return scanner.next();
    }

    public void scannerCloser (){
        scanner.close();
    }

}
