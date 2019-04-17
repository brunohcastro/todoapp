package br.com.todo.todoapp.controller;

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
@Scope("session")
public class TodoController implements Serializable {

    private enum TodoFilter {
        COMPLETED,
        PENDING
    }

    private Todo todo = new Todo();

    private List<Todo> todos = new ArrayList<>();

    private final TodoService todoService;
    private Todo selected;

    private long todoCount;
    private long completedTodosCount;
    private long pendingTodosCount;

    private TodoFilter filter = null;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostConstruct
    public void init() {
        loadData();
    }

    /*
     * Finding
     */

    /**
     * Load TODOS respecting the active filters.
     */
    private void loadTodos() {
        if (this.filter == TodoFilter.PENDING) {
            todos = todoService.findPending();
        } else if (this.filter == TodoFilter.COMPLETED) {
            todos = todoService.findCompleted();
        } else {
            todos = todoService.findAll();
        }
    }

    private void loadData() {
        completedTodosCount = todoService.countCompleted();
        pendingTodosCount = todoService.countPending();
        todoCount = todoService.count();

        loadTodos();
    }

    public Boolean hasTodos() {
        return this.todos.size() > 0;
    }

    /*
     * Filtering
     */
    public void filterCompleted() {
        if (this.filter == TodoFilter.COMPLETED) {
            clearFilters();
            return;
        }

        this.filter = TodoFilter.COMPLETED;
        loadTodos();
    }

    public void filterPending() {
        if (this.filter == TodoFilter.PENDING) {
            clearFilters();
            return;
        }

        this.filter = TodoFilter.PENDING;
        loadTodos();
    }

    private void clearFilters() {
        this.filter = null;
        loadTodos();
    }

    public boolean canFilter(TodoFilter filterType) {
        if (filterType == TodoFilter.COMPLETED) {
            return completedTodosCount > 0 || filter == TodoFilter.COMPLETED;
        }

        if (filterType == TodoFilter.PENDING) {
            return pendingTodosCount > 0 || filter == TodoFilter.PENDING;
        }

        return true;
    }

    /*
     * Deleting
     */
    public void delete(Todo todo) {
        todoService.delete(todo.getId());
        loadData();
    }

    public void deleteAll() {
        todoService.deleteAll();
        loadData();
    }

    public void deleteCompleted() {
        todoService.deleteCompleted();

        loadData();
    }

    /*
     * Editing
     */
    public void edit(Todo todo) {
        this.selected = todo;
    }

    public Boolean isEditing(Todo todo) {
        return this.selected != null && this.selected.equals(todo);
    }

    public void cancelEdit() {
        this.selected = null;
    }

    public void save(Todo todo) {
        todoService.save(todo);
        this.selected = null;

        loadData();
    }

    /*
     * Creating
     */
    public void create() {
        todoService.save(todo);
        this.todo = new Todo();

        loadData();
    }

    public void clearTodo() {
        this.todo = new Todo();
    }

    public List<Todo> getTodos() {
        return todos;
    }

    /*
     * Getters and Setters
     */
    public Todo getTodo() {
        return todo;
    }

    public Todo getSelected() {
        return selected;
    }

    public void setSelected(Todo selected) {
        this.selected = selected;
    }

    public long getCompletedTodosCount() {
        return completedTodosCount;
    }

    public long getPendingTodosCount() {
        return pendingTodosCount;
    }

    public TodoFilter getCompletedFilter() {
        return TodoFilter.COMPLETED;
    }

    public TodoFilter getPendingFilter() {
        return TodoFilter.PENDING;
    }

    public TodoFilter getFilter() {
        return filter;
    }

    public long getTodoCount() {
        return todoCount;
    }
}
