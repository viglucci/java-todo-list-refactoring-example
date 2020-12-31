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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldQuit = false;
        String input = "";

        System.out.println();
        System.out.println("Commands:");
        System.out.println("- exit");
        System.out.println("- add");
        System.out.println("- list");
        System.out.println("- save");
        System.out.println();

        ToDoList userToDoList = new ToDoList();

        List<Task> theTaskList = new LinkedList<>();

        while (!shouldQuit) {

            System.out.print("> ");

            try {
                input = scanner.next();
            } catch (InputMismatchException ex) {
                System.out.println(ex.getMessage());
            }

            if (input.equals("add")) {

                String description = "";

                System.out.print("Enter description: ");

                try {
                    description = scanner.next();
                } catch (InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }

                Task userTask = new Task(description, false, "Low");

                // Add to List
                theTaskList.add(userTask);

                // Sort, reverse
                Collections.sort(theTaskList);
                Collections.reverse(theTaskList);

                userToDoList.setToDoList(theTaskList);

                Iterator<Task> itr = theTaskList.iterator();

                int counter = 1;

                String listSringAccumulator = "";

                while (itr.hasNext()) {
                    listSringAccumulator += counter + ": " + itr.next() + "\n";
                    counter++;
                }

                System.out.print(listSringAccumulator);
            } else if (input.equals("list")) {
                Iterator<Task> itr = theTaskList.iterator();

                int counter = 1;

                String listStringAccumulator = "";

                while (itr.hasNext()) {
                    listStringAccumulator += counter + ": " + itr.next() + "\n";
                    counter++;
                }

                System.out.print(listStringAccumulator);
            } else if (input.equals("save")) {
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
            } else if (input.equals("exit")) {
                shouldQuit = true;
            }
        }

        System.out.println();
        System.out.println("Exiting...");
    }
}
