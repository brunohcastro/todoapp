package br.com.todo.todoapp.repository;

import br.com.todo.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    long countTodosByCompletedTrue();

    long countTodosByCompletedFalse();

    List<Todo> findAllByCompletedTrue();

    List<Todo> findAllByCompletedFalse();

    void deleteAllByCompletedTrue();

    @Modifying
    @Query("update Todo t set t.completed = true")
    void markAllAsCompleted();

    @Modifying
    @Query("update Todo t set t.completed = false")
    void markAllAsPending();
}
