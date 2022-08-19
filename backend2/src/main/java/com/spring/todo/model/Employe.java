package com.spring.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employe implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


    private String name;

    private String Function ;


    private String email;


    private String password;

    public void setFunction(String function) {
        Function = function;
    }

    public String getFunction() {
        return Function;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "adminId")
    @JsonIgnore
    private User admin;

    @OneToMany(mappedBy = "employe", fetch = FetchType.EAGER)
    private List<Todo> todoList;
}
