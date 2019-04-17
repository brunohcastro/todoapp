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
@Scope("view")
public class TodoController implements Serializable {

    private enum TodoFilter {
        COMPLETED,
        PENDING
    }

    private Todo todo = new Todo();
    private Todo selected;

    private List<Todo> todos = new ArrayList<>();

    private long todoCount;
    private long completedTodosCount;
    private long pendingTodosCount;

    private TodoFilter filter = null;

    private final TodoService todoService;

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
     * Load TODOS respecting the active filter.
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

    /**
     * Load all necessary data from the database
     */
    private void loadData() {
        completedTodosCount = todoService.countCompleted();
        pendingTodosCount = todoService.countPending();
        todoCount = todoService.count();

        loadTodos();
    }

    /**
     * Checks if the todos list is not empty.
     *
     * @return true if todos size is higher than 0.
     */
    public Boolean hasTodos() {
        return this.todos.size() > 0;
    }

    /*
     * Filtering
     */

    /**
     * Load only completed todos and sets the filter to {@link TodoFilter#COMPLETED} if not already
     * set. Clear the filter otherwise.
     */
    public void toggleCompletedFilter() {
        if (this.filter == TodoFilter.COMPLETED) {
            clearFilters();
            return;
        }

        this.filter = TodoFilter.COMPLETED;
        loadTodos();
    }

    /**
     * Load only pending todos and sets the filter to {@link TodoFilter#PENDING} if not already
     * set. Clear the filter otherwise.
     */
    public void togglePendingFilter() {
        if (this.filter == TodoFilter.PENDING) {
            clearFilters();
            return;
        }

        this.filter = TodoFilter.PENDING;
        loadTodos();
    }

    /**
     * Remove any applied filter and reload all todos
     */
    private void clearFilters() {
        this.filter = null;
        loadTodos();
    }

    /**
     * Checks if the given filter can be applied based on the amount of
     * todos to be filtered.
     *
     * @param filterType Filter type to check
     * @return true if the filter doesn't return a empty list of todos
     */
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

    /**
     * Check if given instance is beign edited
     *
     * @param todo the instance to be checked
     * @return true if tod'o is selected for edition
     */
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
     * Updating
     */

    /**
     * Toggle all todos status
     */
    public void toggleAllTodoStatus() {
        if (todoCount == 0) {
            return;
        }

        if (getAllCompleted()) {
            todoService.markAllAsPending();
        } else {
            todoService.markAllAsCompleted();
        }

        loadData();
    }

    /**
     * Check if every tod'o is marked as completed
     * @return true if all todos are marked as completed
     */
    public Boolean getAllCompleted() {
        return this.todoCount > 0 && this.completedTodosCount == this.todoCount;
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

    /*
     * Getters and Setters
     */
    public List<Todo> getTodos() {
        return todos;
    }

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
