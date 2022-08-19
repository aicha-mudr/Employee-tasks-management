package com.spring.todo.dto;

import com.spring.todo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;

    @JsonIgnore
    private List<EmployeDto> employe;

    public static User toEntity(UserDto userDto) {
        final User user = new User();

        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());





        return user;
    }

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .employe(
                        user.getEmploye() != null ? user.getEmploye().stream().map(EmployeDto::fromEntity).collect(Collectors.toList()) : null
                )


                .build();
    }
}
