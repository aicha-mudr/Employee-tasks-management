package com.spring.todo.services;

import com.spring.todo.dto.EmployeDto;

import java.util.List;

public interface EmployeService {

    EmployeDto save(EmployeDto category);

    List<EmployeDto> findAll();

    EmployeDto findById(Long id);

    List<EmployeDto> findAllByAdminId(Long adminId);

    List<EmployeDto> findAllByUserId(Long userId);

    void delete(Long id);

    List<EmployeDto> getAllTodoByCategoriesForToday(Long userId);

}
