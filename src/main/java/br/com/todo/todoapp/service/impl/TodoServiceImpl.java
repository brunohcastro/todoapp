package br.com.todo.todoapp.service.impl;

import br.com.todo.todoapp.model.Todo;
import br.com.todo.todoapp.repository.TodoRepository;
import br.com.todo.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("todoService")
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    @Autowired
    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Todo> find() {
        return this.repository.findAll();
    }

    @Override
    public Todo save(Todo todo) {
        return this.repository.save(todo);
    }


    @Override
    public void remove(Todo todo) {
        this.repository.delete(todo);
    }
}
