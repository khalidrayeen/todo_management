package com.spring_todo.todo.service;

import com.spring_todo.todo.dto.todoDto;

import java.util.List;

public interface todoService {

   todoDto addTodo(todoDto todoDto) ;
   todoDto getTodo(Long id);

   List<todoDto> getAlltodo();

   todoDto UpdateTodo(todoDto todoDto , Long id);

 String DeleteTodo(Long id);

 todoDto Compelete(Long id);

}
