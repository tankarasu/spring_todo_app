import com.tankarau.spring_todo_app.models.Task;

import java.util.Arrays;
import java.util.List;

/**
 * FOR TESTING PURPOSE and FIXTURES inside UT/FT
 */
public class Fixture {
    private Fixture() {
        // For now, we don't need the constructor,
        // these class is for testing purpose
    }

    public static List<Task> initTaskList(){
        return Arrays.asList(
                new Task("1", "task 1"),
                new Task("2", "task 2"),
                new Task("3", "task 3")
        );
    }

    public static Task taskOne(){
        return new Task("1", "task 1");
    }

    public static  List<Task> taskListFourEntry(){
        return Arrays.asList(
                new Task("1", "task 1"),
                new Task("2", "task 2"),
                new Task("3", "task 3"),
                new Task("4", "task 4")
        );
    }

    public static List<Task> taskListAfterDelete(){
        return Arrays.asList(
                new Task("1", "task 1"),
                new Task("2", "task 2")
        );
    }

    public static String initTaskListJSON(){
        return "[{\"id\":\"1\",\"title\":\"task 1\"," +
                "\"finished\":false},{\"id\":\"2\",\"title\":\"task 2\"," +
                "\"finished\":false},{\"id\":\"3\",\"title\":\"task 3\"," +
                "\"finished\":false}]";
    }
}
