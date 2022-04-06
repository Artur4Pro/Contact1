package view;

import controller.PersonController;
import controller.Record;
import model.ContactsList;

import java.util.Scanner;

public class Application {
    ContactsList contactsList = new ContactsList();
    Scanner scanner = new Scanner(System.in);

    public void start() {
        Record.readRecord(contactsList);
        System.out.println("\u001B[35m" + """
                                
                ╦ ╦┌─┐┬  ┬  ┌─┐┌─┐┌┬┐┌─┐  ┌┬┐┌─┐  ╔═╗┬ ┬┌─┐┌┐┌┌─┐╔╗ ┌─┐┌─┐┬┌─  ┌─┐┌─┐┌─┐
                ║║║├┤ │  │  │  │ ││││├┤    │ │ │  ╠═╝├─┤│ ││││├┤ ╠╩╗│ ││ │├┴┐  ├─┤├─┘├─┘
                ╚╩╝└─┘┴─┘┴─┘└─┘└─┘┴ ┴└─┘   ┴ └─┘  ╩  ┴ ┴└─┘┘└┘└─┘╚═╝└─┘└─┘┴ ┴  ┴ ┴┴  ┴ \n""" + "\u001B[0m");

        boolean on = true;
        while (on) {
            System.out.println("""
                    1. New contact 🔨
                    2. Read contacts 📖
                    3. Edit contact 🔧
                    4. Delete contact ✖
                    5. Search 🔍
                    6. Exit 🏁""");
            String stepOne = scanner.next();
            switch (stepOne) {
                case "1" -> contactsList.prepareNewUser();
                case "2" -> contactsList.printUsers();
                case "3" -> contactsList.editContact();
                case "4" -> contactsList.deleteContact();
                case "5" -> contactsList.search();
                case "6" -> {
                    Record.writeRecord(contactsList);
                    scanner.close();
                    contactsList.exit();

                    System.out.println("\u001B[35m" + """
                            ╔╦╗┬ ┬┌─┐┌┐┌┬┌─┌─┐  ┌─┐┌┐┌┌┬┐  ╔═╗┌─┐┌─┐┌┬┐  ╔╗ ┬ ┬  ┬
                             ║ ├─┤├─┤│││├┴┐└─┐  ├─┤│││ ││  ║ ╦│ ││ │ ││  ╠╩╗└┬┘  │
                             ╩ ┴ ┴┴ ┴┘└┘┴ ┴└─┘  ┴ ┴┘└┘─┴┘  ╚═╝└─┘└─┘─┴┘  ╚═╝ ┴   o""" + "\u001B[0m");

                    on = false;
                }
                default -> {
                    System.err.println("\nWrong Input ! Try Again\n");
                }
            }
        }
    }
}

