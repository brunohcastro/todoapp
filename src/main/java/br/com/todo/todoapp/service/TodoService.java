package br.com.todo.todoapp.service;

import br.com.todo.todoapp.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    Optional<Todo> find(Integer id);

    List<Todo> findAll();

    long count();

    List<Todo> findCompleted();

    List<Todo> findPending();

    Todo save(Todo todo);

    void delete(Integer id);

    long countCompleted();

    long countPending();

    void deleteCompleted();

    void deleteAll();
}
