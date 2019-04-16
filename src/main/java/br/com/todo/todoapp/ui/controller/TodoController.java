package br.com.todo.todoapp.ui.controller;

import br.com.todo.todoapp.model.Todo;
import br.com.todo.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("view")
public class TodoController implements Serializable {

    private Todo todo = new Todo();

    private List<Todo> todos = new ArrayList<>();

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostConstruct
    public void init() {
        loadTodos();
    }

    private void loadTodos() {
        todos = this.todoService.find();
    }

    public void removeTodo(Todo todo) {
        todoService.remove(todo);
        todos = this.todoService.find();
    }

    public void createTodo() {
        todoService.save(todo);
        todo = new Todo();

        loadTodos();
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public Todo getTodo() {
        return todo;
    }
}
