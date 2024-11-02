package com.spring_todo.todo.controller;


import com.spring_todo.todo.dto.todoDto;
import com.spring_todo.todo.service.todoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
@AllArgsConstructor
public class TodoController {

    private todoService todoService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
     public ResponseEntity<todoDto> addTodo(@RequestBody todoDto todoDto){

        todoDto savedtodoDto=  todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedtodoDto, HttpStatus.CREATED);

     }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
     @GetMapping("{id}")
    public ResponseEntity<todoDto> getTodo(@PathVariable("id")Long todoID){

        todoDto todoDto = todoService.getTodo(todoID);

        return new ResponseEntity<>(todoDto,HttpStatus.OK);


    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<todoDto>> getAllTodo(){

        List<todoDto> tododto = todoService.getAlltodo();

        return new ResponseEntity<>(tododto,HttpStatus.OK);

    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<todoDto> updateTodo(@RequestBody todoDto todoDto , @PathVariable("id") Long Id){

       todoDto todoDto1= todoService.UpdateTodo(todoDto,Id);

       return new ResponseEntity<>(todoDto1,HttpStatus.OK);

    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long Id){

       String message =  todoService.DeleteTodo(Id);

       return new ResponseEntity<>(message,HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<todoDto> compelete(@PathVariable("id") Long Id){

       todoDto todoDto = todoService.Compelete(Id);

       return new ResponseEntity<>(todoDto,HttpStatus.OK);

    }


}
