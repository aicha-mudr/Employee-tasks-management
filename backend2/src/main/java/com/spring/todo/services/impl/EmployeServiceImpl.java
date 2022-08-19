package com.spring.todo.services.impl;

import com.spring.todo.dto.EmployeDto;
import com.spring.todo.exception.EntityNotFoundException;
import com.spring.todo.exception.ErrorCodes;
import com.spring.todo.exception.InvalidEntityException;
import com.spring.todo.repositories.EmployeRepository;
import com.spring.todo.services.EmployeService;
import com.spring.todo.validators.EmployeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    private EmployeRepository categoryRepository;

    @Override
    public EmployeDto save(EmployeDto category) {
        List<String> errors = EmployeValidator.validateCategory(category);
        if (!errors.isEmpty()) {
            log.error("Category is not valid {}", category);
            throw new InvalidEntityException("Category is not valid", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return EmployeDto.fromEntity(categoryRepository.save(EmployeDto.toEntity(category)));
    }

    @Override
    public List<EmployeDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(EmployeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeDto findById(Long id) {
        if (id == null) {
            log.error("Category id is null");
            return null;
        }
        return categoryRepository.findById(id).map(EmployeDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No Category found with ID = " + id, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public List<EmployeDto> findAllByAdminId(Long adminId) {
        return categoryRepository.findCategoryByAdminId(adminId).stream()
                .map(EmployeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeDto> findAllByUserId(Long userId) {
        return categoryRepository.findCategoryByUserId(userId).stream()
                .map(EmployeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Category id is null");
            return;
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<EmployeDto> getAllTodoByCategoriesForToday(Long userId) {
        return categoryRepository.getAllTodoByCategoriesForToday(ZonedDateTime.now().withHour(0).withMinute(0),
                ZonedDateTime.now().withHour(23).withMinute(59), userId)
                .stream()
                .map(EmployeDto::fromEntity)
                .collect(Collectors.toList());
    }
}
