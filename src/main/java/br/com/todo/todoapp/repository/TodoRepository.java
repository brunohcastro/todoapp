package br.com.todo.todoapp.repository;

import br.com.todo.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    long countTodosByCompletedTrue();

    long countTodosByCompletedFalse();

    List<Todo> findAllByCompletedTrue();

    List<Todo> findAllByCompletedFalse();

    void deleteAllByCompletedTrue();

}
