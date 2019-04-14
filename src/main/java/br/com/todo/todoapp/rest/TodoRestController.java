package br.com.todo.todoapp.rest;

import br.com.todo.todoapp.model.Todo;
import br.com.todo.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/todos")
public class TodoRestController {

    @Autowired
    private TodoRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> find() {
        return this.repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Todo create(@RequestBody() Todo todo) {
        return this.repository.save(todo);
    }
}
