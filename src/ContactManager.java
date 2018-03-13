import java.util.List;
import java.util.ArrayList;
import util.FileHelper;
import util.Input;

class ContactManager {
    
    // Constants
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String LINE_BREAK = String.format("%s==========================================%s", ANSI_GREEN, ANSI_RESET);
    private static final String DIVIDER = String.format("%s------------------------------------------%s", ANSI_BLUE, ANSI_RESET);
    private static final String CONTACTS_FILE = "data/contacts.txt";
    private static final String[] MENU_OPTS = {
        "View contacts.", 
        "Add a new contact.", 
        "Search a contact by name.", 
        "Delete an existing contact.", 
        "Exit."
    };

    // Variables
    private static Input in = new Input();
    private static List<Contact> contactList = new ArrayList<>();

    private static String prettyNum(String n) {
        return String.format("%s-%s-%s-%s", n.charAt(0), n.substring(1,4), n.substring(4,7), n.substring(7));
    }

    // Operation Methods...
    private static void showContacts() {
        System.out.println(LINE_BREAK);
        System.out.printf("%-25s | %-11s\n", "Name", "Phone Number");
        for(Contact c: contactList) 
            System.out.printf("%-25s | %-11s\n", c.getName(), prettyNum(c.getNumber()));
        System.out.println(LINE_BREAK);
    }

    private static void searchContact() {
        String search = in.getString("Type a name to search: ");
        for(Contact c: contactList) {
            if (search.equalsIgnoreCase(c.getName())) {
                System.out.println(LINE_BREAK);
                System.out.printf("%s%-25s | %-11s%s\n", ANSI_BLUE, "Name", "Phone Number", ANSI_RESET);
                System.out.println(DIVIDER);
                System.out.printf("%-25s | %-11s\n", c.getName(), prettyNum(c.getNumber()));
                System.out.println(LINE_BREAK);
                return;
            }
        }
        System.out.println("Sorry, that search returned no results");
        searchContact();
    }

    private static void addContact() {
        String name = in.getString("Enter a name: ");
        String num = in.getString("Enter an 11 digit phone number: ");
        if (name.length() <= 25 && num.length() == 11) {
            contactList.add(new Contact(name, num));
        } else {
            System.out.println("Something wasn't right...");
            addContact();
        }
    }

    private static void deleteContact() {
        String searchToDelete = in.getString("Type a name to search and delete: ");
        for (Contact c: contactList) {
            if (searchToDelete.equalsIgnoreCase(c.getName())) {
                contactList.remove(c);
                System.out.printf("%sDeleted FOREVER.%s\n", ANSI_RED, ANSI_RESET);
                return;
            }
        }
        System.out.println("Sorry, search returned no results");
        deleteContact();
    }

    public static void main(String args[]) {

        // Dwight
        for (String line: FileHelper.slurp("data/dwight")) 
            System.out.printf("%s%s%s\n", ANSI_GREEN, line, ANSI_RESET);

        // Read Contact File
        for (String line: FileHelper.slurp(CONTACTS_FILE)) {
            String[] info = line.split(":");
            contactList.add(new Contact(info[0], info[1]));
        }

        // Main Loop
        for(boolean shouldContinue = true; shouldContinue;) {
           
            for(int i = 0; i < MENU_OPTS.length; i++)
                System.out.printf("%d %s\n", i + 1, MENU_OPTS[i]);

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
                    System.out.printf("%sFine. \"You can't handle my undivided attention. Goodbye.\" - Dwight Schrute%s\n", ANSI_PURPLE, ANSI_RESET);
                    shouldContinue = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;
            }
        }

        // Spit Changes To File
        List<String> out_data = new ArrayList<>();
        for(Contact c: contactList)
            out_data.add(String.format("%s:%s", c.getName(), c.getNumber()));
        
        FileHelper.spit(CONTACTS_FILE, out_data);
    }
}
