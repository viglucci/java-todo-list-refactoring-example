import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_HELP = "help";

    private static final List<Task> theTaskList = new LinkedList<>();

    private static boolean shouldQuit = false;
    private static ToDoList userToDoList = new ToDoList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input = "";

        printHelpMenu();

        while (!shouldQuit) {

            System.out.print("> ");

            try {
                input = scanner.next();
            } catch (InputMismatchException ex) {
                System.out.println(ex.getMessage());
            }

            switch (input) {
                case COMMAND_ADD:
                    handleAddCommand();
                    break;
                case COMMAND_LIST:
                    handleListCommand();
                    break;
                case COMMAND_SAVE:
                    handleSaveCommand();
                    break;
                case COMMAND_EXIT:
                    handleQuitCommand();
                    break;
                case COMMAND_HELP:
                    handleHelpCommand();
                    break;
                default:
                    handleUnsupportedCommand(input);
            }
        }

        System.out.println();
        System.out.println("Exiting...");
    }

    private static void printHelpMenu() {
        System.out.println();
        System.out.println("Commands:");
        System.out.println("- " + COMMAND_ADD);
        System.out.println("- " + COMMAND_LIST);
        System.out.println("- " + COMMAND_SAVE);
        System.out.println("- " + COMMAND_EXIT);
        System.out.println("- " + COMMAND_HELP);
        System.out.println();
    }

    private static void handleHelpCommand() {
        printHelpMenu();
    }

    private static void handleUnsupportedCommand(String input) {
        System.out.println();
        System.out.println("Unknown command \"" + input + "\". Enter \"help\" to list available commands.");
        System.out.println();
    }

    private static void handleQuitCommand() {
        shouldQuit = true;
    }

    private static void handleListCommand() {
        printList();
    }

    private static void handleSaveCommand() {
        String filePath = System.getProperty("user.dir") + "\\save\\toDoList.txt";

        System.out.println(filePath);

        File toDoFile = new File(filePath);

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(toDoFile, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            Iterator<Task> itr = userToDoList.getToDoList().iterator();

            int counter = 1;
            String listStringAccumulator = "";

            while (itr.hasNext()) {
                listStringAccumulator += counter + ": " + itr.next() + "\n";
                counter++;
            }

            LocalDate localDate = LocalDate.now();
            printWriter.println("----------------------------");
            printWriter.println("to-do-list: " + localDate);
            printWriter.println(listStringAccumulator);
            printWriter.println("");
            printWriter.println("");

            printWriter.close();
            fileWriter.close();

            System.out.println("File saved!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void handleAddCommand() {
        String description = "";

        System.out.print("Enter description: ");

        try {
            description = scanner.next();
        } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
        }

        Task userTask = new Task(description, false, "Low");

        theTaskList.add(userTask);

        Collections.sort(theTaskList);
        Collections.reverse(theTaskList);

        userToDoList.setToDoList(theTaskList);

        printList();
    }

    private static void printList() {
        Iterator<Task> itr = theTaskList.iterator();

        int counter = 1;

        StringBuilder output = new StringBuilder();

        while (itr.hasNext()) {
            output
                .append(counter)
                .append(": ")
                .append(itr.next())
                .append("\n");
            counter++;
        }

        System.out.print(output);
    }
}
