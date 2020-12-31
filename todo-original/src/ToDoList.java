import java.util.LinkedList;
import java.util.List;

public class ToDoList {
    private List<Task> toDoList;

    public ToDoList() {
        this.toDoList = new LinkedList<>();
    }

    public List<Task> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<Task> toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public String toString() {
        return "ToDoList{" +
            "toDoList=" + toDoList +
            '}';
    }
}
