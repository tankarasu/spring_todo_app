// Internal requirements

import com.tankarau.spring_todo_app.models.Task;
import com.tankarau.spring_todo_app.services.TaskService;

// Third-Party requirements
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Core-module requirements
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


class TaskServiceTest {
    private final TaskService taskService = new TaskService();

    @BeforeEach
    public void initEach() {
        taskService.setTasks(new ArrayList<>(Fixture.initTaskList()));
    }

    @Test
    void fetchAllTasks() {
        // GIVEN there's some tasks to fetch
        // THEN we should have the Tasks fetched
        assertThat(taskService.fetchAllTasks())
                .usingRecursiveComparison()
                .isEqualTo(Fixture.initTaskList());
    }

    @Test
    void fetchSpecificTask() {
        // GIVEN we want to fetch one specific Task
        // WHEN we invoke method with specific ID
        String expectedID = "1";

        // THEN we should fetch the specified task
        assertThat(taskService.fetchSpecificTask(expectedID))
                .usingRecursiveComparison()
                .isEqualTo(Fixture.taskOne());
    }

    @Test
    void createOneTask() {
        // GIVEN we want to create a new Task
        String newID = "4";
        String newDescription = "task 4";

        // WHEN we create the new task
        Task newTask = new Task(newID, newDescription);
        taskService.createOneTask(newTask);

        // THEN we should have created the specified task
        assertThat(taskService.fetchAllTasks())
                .usingRecursiveComparison()
                .isEqualTo(Fixture.taskListFourEntry());
    }

    @Test
    void updateSpecificTask() {
        // GIVEN we want update a task description
        String idToUpdate = "1";
        String updatedDescription = "task ONE";

        // WHEN we provide the new description to the specified task
        taskService.updateSpecificTask(idToUpdate, updatedDescription);

        // THEN the description of specified task should be updated
        assertThat(taskService.fetchSpecificTask("1").getTitle())
                .isEqualTo("task ONE");
    }

    @Test
    void deleteSpecificTask() {
        // GIVEN we want to delete a task
        String expectedId = "3";

        // WHEN we invoke method with specified ID
        taskService.deleteSpecificTask(expectedId);

        // THEN the task should be deleted
        assertThat(taskService.fetchAllTasks())
                .usingRecursiveComparison()
                .isEqualTo(Fixture.taskListAfterDelete());
    }

    @Test
    void switchFinishedState() {
        // GIVEN we have a specific not finished task
        String expectedId = "1";

        // WHEN we want to change the state to finished
        taskService.switchFinishedState(expectedId);

        // THEN the task state must change
        assertThat(taskService.fetchSpecificTask("1").getFinished())
                .isTrue();
    }
}