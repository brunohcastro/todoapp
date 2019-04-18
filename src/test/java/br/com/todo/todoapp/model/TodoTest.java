package br.com.todo.todoapp.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TodoTest {

    @Test
    public void toggleStatus() {
        Todo todo = new Todo();
        todo.setCompleted(true);

        todo.toggleStatus();

        assertFalse(todo.getCompleted());
    }
}