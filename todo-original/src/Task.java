public class Task implements Comparable<Task> {

    private String taskDescription;
    private boolean timeSensitive;
    private String levelOfImportance;

    private int priorityLevel;

    public Task(String taskDescription, boolean timeSensitive, String levelOfImportance) {
        this.taskDescription = taskDescription;
        this.timeSensitive = timeSensitive;
        this.levelOfImportance = levelOfImportance;
        setPriorityLevel(timeSensitive, levelOfImportance);
    }

    // Methods
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean getTimeSensitive() {
        return timeSensitive;
    }

    public void setTimeSensitive(boolean timeSensitive) {
        this.timeSensitive = timeSensitive;
    }

    public String getLevelOfImportance() {
        return levelOfImportance;
    }

    public void setLevelOfImportance(String levelOfImportance) {
        this.levelOfImportance = levelOfImportance;
    }

    // setPriorityLevel
    public void setPriorityLevel(boolean timeSensitive, String levelOfImportance) {
        // Low to High
        // ts false && LOI low          1
        if (timeSensitive == false && levelOfImportance.equals("Low")) {
            priorityLevel = 1;
        }
        // ts false && LOI medium       2
        else if (timeSensitive == false && levelOfImportance.equals("Medium")) {
            priorityLevel = 2;
        }
        // ts false && LOI high         3
        else if (timeSensitive == false && levelOfImportance.equals("High")) {
            priorityLevel = 3;
        }
        // ts true && LOI low           4
        else if (timeSensitive == true && levelOfImportance.equals("Low")) {
            priorityLevel = 4;
        }
        // ts true && LOI medium        5
        else if (timeSensitive == true && levelOfImportance.equals("Medium")) {
            priorityLevel = 5;
        }
        // ts true && LOI high          6
        else if (timeSensitive == true && levelOfImportance.equals("High")) {
            priorityLevel = 6;
        }
    }

    // compareTo method
    public int compareTo(Task t) {
        return Integer.compare(priorityLevel, t.priorityLevel);
    }

    // toString
    public String toString() {
        return taskDescription;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
}
