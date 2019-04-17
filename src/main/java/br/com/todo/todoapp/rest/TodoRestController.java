package br.com.todo.todoapp.rest;

import br.com.todo.todoapp.model.Todo;
import br.com.todo.todoapp.repository.TodoRepository;
import br.com.todo.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

    private TodoService service;

    @Autowired
    public TodoRestController(TodoService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> find() {
        return this.service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Todo save(@RequestBody() Todo todo) {
        return this.service.save(todo);
    }

    public long countCompleted() {
        return this.service.countCompleted();
    }

    public long countPending() {
        return this.service.countPending();
    }

    public long countAll() {
        return this.service.count();
    }

    public List<Todo> findCompleted() {
        return this.service.findCompleted();
    }

    public List<Todo> findPending() {
        return this.service.findPending();
    }

    public void delete(Integer id) {
        this.service.delete(id);
    }

    public void deleteCompleted() {
        this.service.deleteCompleted();
    }

    public void deleteAll() {
        this.service.deleteAll();
    }

}
