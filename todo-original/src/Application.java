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

    private static final List<Task> theTaskList = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldQuit = false;
        String input = "";

        System.out.println();
        System.out.println("Commands:");
        System.out.println("- " + COMMAND_ADD);
        System.out.println("- " + COMMAND_LIST);
        System.out.println("- " + COMMAND_SAVE);
        System.out.println("- " + COMMAND_EXIT);
        System.out.println();

        ToDoList userToDoList = new ToDoList();

        while (!shouldQuit) {

            System.out.print("> ");

            try {
                input = scanner.next();
            } catch (InputMismatchException ex) {
                System.out.println(ex.getMessage());
            }

            if (input.equals(COMMAND_ADD)) {

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
            } else if (input.equals(COMMAND_LIST)) {
                printList();
            } else if (input.equals(COMMAND_SAVE)) {
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
            } else if (input.equals(COMMAND_EXIT)) {
                shouldQuit = true;
            }
        }

        System.out.println();
        System.out.println("Exiting...");
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
