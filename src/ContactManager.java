import java.util.List;
import java.util.ArrayList;

import util.FileHelper;
import util.Input;

class ContactManager {

    private static Input in = new Input();
    private static final String[] MENU_OPTS = {
        "View contacts.", 
        "Add a new contact.", 
        "Search a contact by name.", 
        "Delete an existing contact.", 
        "Exit."
    };

    private static final String LINE_BREAK = "------------------------------";

    private static List<String> contactList = new ArrayList<>();

    private static void showContacts() {
        // TODO
    }

    private static void searchContact() {
        // TODO
    }

    private static void addContact() {
        // TODO
    }

    private static void deleteContact() {
        // TODO
    }

    public static void main(String args[]) {

        for(boolean shouldContinue = true; shouldContinue;) {
           
            System.out.println(LINE_BREAK);
            for(int i = 0; i < MENU_OPTS.length; i++)
                System.out.printf("%d %s\n", i + 1, MENU_OPTS[i]);
            System.out.println(LINE_BREAK);

            int userOpt = in.getInt("Enter an option (1, 2, 3, 4 or 5): ");

            switch (userOpt) {
                case 1:
                    showContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    shouldContinue = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;
            }
        }
    }
}
