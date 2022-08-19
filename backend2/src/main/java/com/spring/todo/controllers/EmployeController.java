package com.spring.todo.controllers;

import com.spring.todo.controllers.api.CategoryApi;
import com.spring.todo.dto.EmployeDto;
import com.spring.todo.dto.TodoDto;
import com.spring.todo.services.EmployeService;
import com.spring.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeController implements CategoryApi {

    @Autowired
    private TodoService todoService;

    @Autowired
    private EmployeService categoryService;

    @Override
    public ResponseEntity<EmployeDto> createCategory(EmployeDto categoryDto) {
        return new ResponseEntity<>(categoryService.save(categoryDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EmployeDto> updateCategory(EmployeDto categoryDto) {
        return new ResponseEntity<>(categoryService.save(categoryDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<EmployeDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TodoDto>> getAllTodoByCategoriesId(Long id) {
        return new ResponseEntity<>(todoService.findByCategory(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmployeDto>> getAllCategoriesByAdminId(Long id) {
        return new ResponseEntity<>(categoryService.findAllByAdminId(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmployeDto>> getAllCategoriesByUserId(Long id) {
        return new ResponseEntity<>(categoryService.findAllByUserId(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeDto> getCategory(Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteCategory(Long id) {
        categoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TodoDto>> getAllTodoByCategoriesForToday(Long userId) {
        return new ResponseEntity(categoryService.getAllTodoByCategoriesForToday(userId), HttpStatus.OK);
    }
}
