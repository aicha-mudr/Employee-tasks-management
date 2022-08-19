package com.spring.todo.dto;

import com.spring.todo.model.Employe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeDto {

    private Long id;


    private String name;


    private String function;

    private String email;


    private String password;

    private UserDto user;
    private UserDto admin;


    private List<TodoDto> todoList;

    public static Employe toEntity(EmployeDto categoryDto) {
        Employe category = new Employe();

        category.setUser(UserDto.toEntity(categoryDto.getUser()));
        category.setId(categoryDto.getId());
        category.setAdmin(UserDto.toEntity(categoryDto.getAdmin()));
        category.setName(categoryDto.getName());
        category.setFunction(categoryDto.getFunction());
        category.setEmail(categoryDto.getEmail());
        category.setPassword(categoryDto.getPassword());

        return category;
    }

    public static EmployeDto fromEntity(Employe employe) {
        return EmployeDto.builder()
                .id(employe.getId())
                .name(employe.getName())

                .function(employe.getFunction())
                .email(employe.getEmail())

                .password(employe.getPassword())

                .todoList(
                        employe.getTodoList() != null ? employe.getTodoList().stream().map(TodoDto::fromEntity).collect(Collectors.toList()) : null
                )
                .build();
    }
}
