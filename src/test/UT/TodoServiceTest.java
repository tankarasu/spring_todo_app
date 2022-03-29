// Internal requirements

import com.tankarau.spring_todo_app.models.Todo;
import com.tankarau.spring_todo_app.services.TodoService;

// Third-party requirements
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// Core-module requirements
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class TodoServiceTest {
    private final TodoService todoService = new TodoService();

    List<Todo> initialTaskList = Arrays.asList(
            new Todo("1", "todo 1"),
            new Todo("2", "todo 2"),
            new Todo("3", "todo 3")
    );

    @BeforeEach
    public void initEach() {
        todoService.setTodos(new ArrayList<>(initialTaskList));
    }

    @Test
    void fetchAllTodos() {
        // GIVEN there's some tasks to fetch
        // THEN we should have the todos fetched
        assertThat(todoService.fetchAllTodos())
                .usingRecursiveComparison()
                .isEqualTo(initialTaskList);
    }

    @Test
    void fetchSpecificTodo() {
        // GIVEN we want to fetch one specific Task
        Todo expectedTodo = new Todo("1", "todo 1");

        // WHEN we invoke method with specific ID
        String expectedID = "1";

        // THEN we should fetch the specified task
        assertThat(todoService.fetchSpecificTodo(expectedID))
                .usingRecursiveComparison()
                .isEqualTo(expectedTodo);
    }

    @Test
    void createOneTodo() {
        // GIVEN we want to create a new Task
        List<Todo> expectedTodoList = Arrays.asList(
                new Todo("1", "todo 1"),
                new Todo("2", "todo 2"),
                new Todo("3", "todo 3"),
                new Todo("4", "todo 4")
        );
        String newID = "4";
        String newDescription = "todo 4";
        Todo newTodo = new Todo(newID, newDescription);
        todoService.createOneTodo(newTodo);

        // THEN we should have created the specified task
        assertThat(todoService.fetchAllTodos())
                .usingRecursiveComparison()
                .isEqualTo(expectedTodoList);
    }

    @Test
    void updateSpecificTask() {
        // GIVEN we want update a task description
        String idToUpdate = "1";
        String updatedDescription = "todo ONE";

        // WHEN we provide the new description to the specified task
        todoService.updateSpecificTask(idToUpdate, updatedDescription);

        // THEN the description of specified task should be updated
        assertThat(todoService.fetchSpecificTodo("1").getTitle())
                .isEqualTo("todo ONE");
    }

    @Test
    void deleteSpecificTodo() {
        // GIVEN we want to delete a task
        List<Todo> finalTaskList = Arrays.asList(
                new Todo("1", "todo 1"),
                new Todo("2", "todo 2")
        );

        // WHEN we invoke method with specified ID
        String expectedId = "3";
        todoService.deleteSpecificTodo(expectedId);

        // THEN the task should be deleted
        assertThat(todoService.fetchAllTodos())
                .usingRecursiveComparison()
                .isEqualTo(finalTaskList);
    }

    @Test
    void switchFinishedState() {
        // GIVEN we have a specific not finished task
        String expectedId = "1";

        // WHEN we want to change the state to finished
        todoService.switchFinishedState(expectedId);

        // THEN the task state must change
        assertThat(todoService.fetchSpecificTodo("1").getFinished())
                .isTrue();
    }
}