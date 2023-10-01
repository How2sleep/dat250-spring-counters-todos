package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest-Endpoint for todos.
 */
@RestController
public class TodoController {

  private List<Todo> todoList = new ArrayList<>();

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";

  @GetMapping("/todos")
  public List<Todo> getTodos() {
    return todoList;
  }

  @GetMapping("/todos/{id}")
    public Object getTodoById(@PathVariable Long id) {
        for (Todo todo : todoList) {
            if (todo.getId().equals(id)) {
                return todo;
            }
        }
        return createJsonErrorMessage(String.format( TODO_WITH_THE_ID_X_NOT_FOUND, id));
    }
  @PostMapping("/todos")
  public Todo createTodo(@RequestBody Todo newTodo) {
      if (newTodo.getId() == null) {
          newTodo.setId((long) (todoList.size() + 1));
      }
    todoList.add(newTodo);
    return newTodo;
  }
  @PutMapping("/todos/{id}")
    public Object updateTodoById(@PathVariable Long id, @RequestBody Todo newTodo) {
        todoList = getTodos();
        for (Todo todo : todoList) {
            if (todo.getId().equals(id)) {
                todo.setSummary(newTodo.getSummary());
                todo.setDescription(newTodo.getDescription());
                return todo;
            }
        }
        return createJsonErrorMessage(String.format( TODO_WITH_THE_ID_X_NOT_FOUND, id));
    }

    @DeleteMapping("/todos/{id}")
    public Object deleteTodoById(@PathVariable Long id) {
        todoList = getTodos();
        for (Todo todo : todoList) {
            if (todo.getId().equals(id)) {
                todoList.remove(todo);
                return todoList;
            }
        }
        return createJsonErrorMessage(String.format( TODO_WITH_THE_ID_X_NOT_FOUND, id));
    }
    static String createJsonErrorMessage(String message) {
        Gson gson = new Gson();
        ErrorMessage errorMessage = new ErrorMessage(message);
        return gson.toJson(errorMessage);
    }

    // Create a class to represent the error message structure
    static class ErrorMessage {
        private String message;

        public ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
