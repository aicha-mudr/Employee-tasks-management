package com.spring.todo.services.impl;

import com.spring.todo.dto.EmployeDto;
import com.spring.todo.dto.TodoDto;
import com.spring.todo.exception.EntityNotFoundException;
import com.spring.todo.exception.ErrorCodes;
import com.spring.todo.exception.InvalidEntityException;
import com.spring.todo.model.Employe;
import com.spring.todo.model.Todo;
import com.spring.todo.repositories.EmployeRepository;
import com.spring.todo.repositories.TodoRepository;
import com.spring.todo.services.TodoService;
import com.spring.todo.validators.TodoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private EmployeRepository categoryRepository;

    @Override
    public TodoDto save(TodoDto todoDto) {
    List<String> errors = TodoValidator.validateTodo(todoDto);
        if (!errors.isEmpty()) {
            log.error("Todo is not valid {}", todoDto);
            throw new InvalidEntityException("Todo is not valid", ErrorCodes.TODO_NOT_VALID, errors);
        }
        return TodoDto.fromEntity(todoRepository.save(TodoDto.toEntity(todoDto)));
    }

    @Override
    public List<TodoDto> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto findById(Long id) {
        if (id == null) {
            log.error("User id is null");
            return null;
        }
        final Long categoryId = categoryRepository.findCategoryByTodoId(id);
        Employe category = new Employe();
        category.setId(categoryId);

        final Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(value -> value.setEmploye(category));

        final TodoDto todoDto = TodoDto.fromEntity(todo.get());
        EmployeDto categoryDto = EmployeDto.fromEntity(category);
        todoDto.setCategory(categoryDto);

        return Optional.of(todoDto).
                orElseThrow(() -> new EntityNotFoundException("No Todo found with ID = " + id, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public List<TodoDto> findByCategory(Long employeId) {
        return todoRepository.findTodoByEmployeId(employeId).stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Todo id is null");
            return;
        }
        todoRepository.deleteById(id);
    }
}
