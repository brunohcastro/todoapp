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

    private Todo selected;

    private Boolean editing;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostConstruct
    public void init() {
        this.editing = false;
        loadTodos();
    }

    private void loadTodos() {
        todos = this.todoService.find();
    }

    public void removeTodo(Todo todo) {
        todoService.remove(todo);
        todos = this.todoService.find();
    }

    public void editTodo(Todo todo) {
        this.selected = todo;
    }

    public void cancelEdit() {
        this.selected = null;
    }

    public void saveTodo(Todo todo) {
        todoService.save(todo);
        this.selected = null;

        loadTodos();
    }

    public void createTodo() {
        todoService.save(todo);
        this.todo = new Todo();

        loadTodos();
    }

    public Boolean isEditing(Todo todo) {
        return this.selected != null && this.selected.equals(todo);
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public Todo getTodo() {
        return todo;
    }

    public Boolean getEditing() {
        return editing;
    }

    public void setEditing(Boolean editing) {
        this.editing = editing;
    }

    public Todo getSelected() {
        return selected;
    }

    public void setSelected(Todo selected) {
        this.selected = selected;
    }
}
