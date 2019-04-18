package br.com.todo.todoapp.dto;

import br.com.todo.todoapp.model.Todo;

import java.util.List;

public class TodoViewDTO {

    private long todoCount;
    private long completedTodosCount;
    private long pendingTodosCount;

    private List<Todo> todos;

    public TodoViewDTO(List<Todo> todos, long todoCount, long completedTodosCount, long pendingTodosCount) {
        this.todoCount = todoCount;
        this.completedTodosCount = completedTodosCount;
        this.pendingTodosCount = pendingTodosCount;
        this.todos = todos;
    }

    public long getTodoCount() {
        return todoCount;
    }

    public long getCompletedTodosCount() {
        return completedTodosCount;
    }

    public long getPendingTodosCount() {
        return pendingTodosCount;
    }

    public List<Todo> getTodos() {
        return todos;
    }
}
