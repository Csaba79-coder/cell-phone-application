package com.csaba79coder;

import com.csaba79coder.model.Contact;
import com.csaba79coder.model.MobilePhone;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class MobileApp {

    private static Scanner scanner= new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("0744 123 456");

    public static void main(String[] args) {

        boolean quit = false;

        startPhone();
        printActions();

        while (!quit) {
            System.out.println("\nEnter an action: (6 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Good bye!\nShutting down ...");
                    quit = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;
            }
        }
    }

    private static void startPhone() {
        System.out.println("Starting phone...");
    }

    private static void printActions() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0 - to shutdown\n" +
                "1 - to print contacts\n" +
                "2 - to add a new contact\n" +
                "3 - to update an existing contact\n" +
                "4 - to remove an existing contact\n" +
                "5 - query if an existing contact exists\n" +
                "6 - to print a list of available actions.");
        System.out.println("Choose your action: ");
    }

    private static void addNewContact() {
        String name = scanInput(scanner, "Enter existing contact name: ");
        String phoneNumber = scanInput(scanner, "Enter existing contact phone number: ");
        Contact currentContact = Contact.createContact(name, phoneNumber);
        if (mobilePhone.addNewContact(currentContact)) {
            System.out.println("New contact added: " + name + ", phone = " + phoneNumber);
        } else {
            System.out.println("Cannot add, " + name + " already on file.");
        }
    }

    private static void updateContact() {
        String name = scanInput(scanner, "Enter existing contact name: ");
        Contact existingContact = mobilePhone.queryContact(name);
        handleContactResult(existingContact);
        String newName = scanInput(scanner, "Enter new contact name: ");
        String newPhoneNumber = scanInput(scanner, "Enter new contact phone number: ");
        Contact upDatedContact = Contact.createContact(newName, newPhoneNumber);
        if (mobilePhone.updateContact(existingContact, upDatedContact)) {
            System.out.println("Successfully updated record.");
        } else {
            System.out.println("Error updating record.");
        }
    }

    private static void queryContact() {
        String name = scanInput(scanner, "Enter existing contact name: ");
        Contact existingContact = mobilePhone.queryContact(name);
        handleContactResult(existingContact);
        System.out.println("Name: " + existingContact.getName() + " phone number is " + existingContact.getPhoneNumber());
    }

    private static void removeContact() {
        String name = scanInput(scanner, "Enter existing contact name: ");
        Contact existingContact = mobilePhone.queryContact(name);
        handleContactResult(existingContact);
        if (mobilePhone.removeContact(existingContact)) {
            System.out.println("Successfully deleted.");
        } else {
            System.out.println("Error deleting contact.");
        }
    }

    private static String scanInput(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    private static void handleContactResult(Contact existingContact) {
        if (existingContact == null) {
            System.out.println("Contact not found.");
        }
    }
}
