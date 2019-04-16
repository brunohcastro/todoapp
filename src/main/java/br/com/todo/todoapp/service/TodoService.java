package br.com.todo.todoapp.service;

import br.com.todo.todoapp.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> find();

    Todo save(Todo todo);

    void remove(Todo todo);
}
