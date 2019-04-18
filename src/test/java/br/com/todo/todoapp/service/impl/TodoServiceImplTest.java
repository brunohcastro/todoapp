package br.com.todo.todoapp.service.impl;

import br.com.todo.todoapp.model.Todo;
import br.com.todo.todoapp.repository.TodoRepository;
import br.com.todo.todoapp.service.TodoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class TodoServiceImplTest {

    @MockBean
    private TodoRepository repository;
    @Autowired
    private TodoService service;
    private Todo todo1;

    @Before
    public void setUp() {
        todo1 = new Todo();
        todo1.setId(1);
        todo1.setDescription("Test");
        todo1.setCompleted(false);
    }

    @After
    public void before() {
        Mockito.reset(repository);
    }

    @Test
    public void toggleStatus__shouldWorkForExistingEntities() {
        Boolean status = todo1.getCompleted();

        Mockito.when(repository.save(Mockito.any(Todo.class)))
                .thenReturn(todo1);

        Mockito.when(repository.findById(1))
                .thenReturn(Optional.ofNullable(todo1));

        service.toggleStatus(1);
        assertEquals(!status, todo1.getCompleted());
    }

    @Test(expected = RuntimeException.class)
    public void toggleStatus__shouldThrowWhenEntityDoesntExist() {
        Mockito.when(repository.findById(4))
                .thenThrow(RuntimeException.class);

        service.toggleStatus(4);
    }

    @Test
    public void toggleAllStatus__shouldCallMarkAllAsCompleted() {
        Mockito.when(repository.count())
                .thenReturn(3L);

        Mockito.when(repository.countTodosByCompletedTrue())
                .thenReturn(1L);

        service.toggleAllStatus();

        Mockito.verify(repository, Mockito.times(1)).markAllAsCompleted();
    }

    @Test
    public void toggleAllStatus__shouldCallMarkAllAsPending() {
        Mockito.when(repository.count())
                .thenReturn(3L);

        Mockito.when(repository.countTodosByCompletedTrue())
                .thenReturn(3L);

        service.toggleAllStatus();

        Mockito.verify(repository, Mockito.times(1)).markAllAsPending();
    }

    @TestConfiguration
    static class TodoServiceImplTestConfig {

        @Bean
        public TodoService todoService(TodoRepository repository) {
            return new TodoServiceImpl(repository);
        }
    }

}