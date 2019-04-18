package br.com.todo.todoapp.rest;

import br.com.todo.todoapp.dto.TodoViewDTO;
import br.com.todo.todoapp.model.Todo;
import br.com.todo.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

    private final TodoService service;

    @Autowired
    public TodoRestController(TodoService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Todo save(@RequestBody() Todo todo) {
        return this.service.save(todo);
    }

    @RequestMapping(method = RequestMethod.GET)
    public TodoViewDTO findAll() {
        return this.loadViewData(this.service.findAll());
    }

    @RequestMapping(path = "/{filter}", method = RequestMethod.GET)
    public TodoViewDTO findFiltered(@PathVariable("filter") String filter) {
        List<Todo> todos;

        if (filter.equals("pending")) {
            todos = this.service.findPending();
        } else if (filter.equals("completed")) {
            todos = this.service.findCompleted();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Filtro inválido, use os filtros 'pending' ou 'completed'.");
        }

        return this.loadViewData(todos);
    }

    @RequestMapping(path = "/toggle-all", method = RequestMethod.PATCH)
    public void toggleAllStatus() {
        this.service.toggleAllStatus();
    }

    @RequestMapping(path = "/{id}/toggle", method = RequestMethod.PATCH)
    public void toggleStatus(@PathVariable("id") Integer id) {
        this.service.toggleStatus(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public void update(@PathVariable("id") Integer id, @RequestBody() Todo todo) {
        this.service.updateDescription(id, todo.getDescription());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        this.service.delete(id);
    }

    @RequestMapping(path = "/completed", method = RequestMethod.DELETE)
    public void deleteCompleted() {
        this.service.deleteCompleted();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        this.service.deleteAll();
    }


    private TodoViewDTO loadViewData(List<Todo> todos) {
        return new TodoViewDTO(
                todos,
                this.service.count(),
                this.service.countCompleted(),
                this.service.countPending()
        );
    }

}
