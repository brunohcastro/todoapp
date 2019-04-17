package br.com.todo.todoapp.service.impl;

import br.com.todo.todoapp.model.Todo;
import br.com.todo.todoapp.repository.TodoRepository;
import br.com.todo.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service("todoService")
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    @Autowired
    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Todo> find(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Todo> findCompleted() {
        return this.repository.findAllByCompletedTrue();
    }

    @Override
    public List<Todo> findPending() {
        return this.repository.findAllByCompletedFalse();
    }

    @Override
    public long count() {
        return this.repository.count();
    }

    @Override
    public long countCompleted() {
        return this.repository.countTodosByCompletedTrue();
    }

    @Override
    public long countPending() {
        return this.repository.countTodosByCompletedFalse();
    }

    @Override
    public Todo save(Todo todo) {
        return this.repository.save(todo);
    }

    @Override
    public void delete(Integer id) {
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

    @Override
    public void deleteCompleted() {
        this.repository.deleteAllByCompletedTrue();
    }

}
