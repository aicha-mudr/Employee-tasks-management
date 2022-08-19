package com.spring.todo.repositories;

import com.spring.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findTodoByEmployeId(Long employeId);
}
