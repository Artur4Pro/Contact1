package model;

import controller.InputMethods;
import model.enumTypes.EmailType;
import model.enumTypes.PhoneNumberType;

import java.util.Map;
import java.util.TreeMap;

public class User  {

    private Person person ;

    public User() {
    }



    /**
     * Key is phone number.
     * Value is name of user.
     */
    static Map<String, String> phoneNumberAndName = new TreeMap<>();


    /**
     * Key is phone number type.
     * Value is phone number.
     */
    static Map<PhoneNumberType, String> phoneNumberTypeAndPhoneNumber = new TreeMap<>();


    /**
     * Key is email.
     * Value is phone number.
     */
    static Map<String, String> emailAndName = new TreeMap<>();


    /**
     * Key is email.
     * Value is email type.
     */
    static Map<String, EmailType> emailAndMailType = new TreeMap<>();


    public User(Person person) {
        this.person = person;
    }

    /**
     * Take arguments and set in phone phoneNumberAndName map.
     *
     * @param phoneNumber phone number.
     * @param name        name.
     */
    public static void phoneNumberAndNameSetter(String phoneNumber, String name) {
        phoneNumberAndName.put(phoneNumber, name);
    }


    /**
     * Take arguments and set in phone phoneNumberTypeAndPhoneNumber map.
     *
     * @param phoneNumber     phone number.
     * @param phoneNumberType phone number type.
     */
    public static void phoneNumberTypeAndPhoneNumberSetter(String phoneNumber, PhoneNumberType phoneNumberType) {
        phoneNumberTypeAndPhoneNumber.put(phoneNumberType, phoneNumber);
    }


    /**
     * Take arguments and set in phone emailAndPhoneNumber map.
     *
     * @param name phone number.
     * @param email       email.
     */
    public static void emailAndNameSetter(String name, String email) {
        emailAndName.put(email, name);
    }

    public static void emailAndEmailTypeSetter(String email , EmailType emailType){
        emailAndMailType.put(email,emailType);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return person.toString();
    }
}
