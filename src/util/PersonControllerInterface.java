package util;

import model.User;

public interface PersonControllerInterface {
    String addName();

    String addMail();

    String addSearchMail();

    String addPhoneNumber();

    String selectPhoneNumberTypeFromMenu();

    String selectEmailTypeFromMenu();

    String selectSearchTypeFromMenu();

    String mailQuestion();

    String creatingQuestion();

    String nameEditQuestion();

    String phoneTypeEditQuestion();

    String eMailEditQuestion();

    String deleteQuestion(User user);

    void scannerCloser();
}
