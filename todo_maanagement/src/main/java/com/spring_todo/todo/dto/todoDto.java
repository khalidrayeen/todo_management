package com.spring_todo.todo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class todoDto {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
}
