package no.hvl.dat250.rest.todos;

import no.hvl.dat250.rest.counters.Counters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;

/**
 * Rest-Endpoint for todos.
 */
@RestController
public class TodoController {

  private Todo todo = new Todo();

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";

  @GetMapping("/todos")
  public Todo getTodos() {
    return todo ;
  }

  @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        if (todo.getId() == id) {
        return todo;
        } else {
        return null;
        }
    }
  @PutMapping("/todos")
  @Ge
  public Todo createTodo() {
    todo = new Todo();
    return todo;
  }

    @DeleteMapping("/todos")
    public Todo deleteTodo() {
        todo = new Todo();
        return todo;
    }

}
