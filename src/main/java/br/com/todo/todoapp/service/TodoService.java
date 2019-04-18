package br.com.todo.todoapp.service;

import br.com.todo.todoapp.model.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();

    long count();

    List<Todo> findCompleted();

    List<Todo> findPending();

    Todo save(Todo todo);

    void updateDescription(Integer id, String description);

    void toggleStatus(Integer id);

    void toggleAllStatus();

    void delete(Integer id);

    long countCompleted();

    long countPending();

    void deleteCompleted();

    void deleteAll();
}
