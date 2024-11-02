package com.spring_todo.todo.service.impl;

import com.spring_todo.todo.dto.todoDto;
import com.spring_todo.todo.entity.todos;
import com.spring_todo.todo.exception.ResourceNotFoundException;
import com.spring_todo.todo.repository.todoRepository;
import com.spring_todo.todo.service.todoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class todoServiceImpl implements todoService {

    private todoRepository todoRepository;

    private ModelMapper modelMapper;
    @Override
    public todoDto addTodo(todoDto todoDto) {

        //convert tododto into todo jpa

//        todos todo = new todos();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.getCompleted());

        todos todo = modelMapper.map(todoDto,todos.class);

        todos savedtodo = todoRepository.save(todo);

        //saved todojpa entity to tododto object entity

//      todoDto savedtodoDto = new todoDto();
//      savedtodoDto.setId(savedtodo.getId());
//      savedtodoDto.setTitle(savedtodo.getTitle());
//      savedtodoDto.setDescription(savedtodo.getDescription());
//      savedtodoDto.setCompleted(savedtodo.isCompleted());

        todoDto savedtodoDto = modelMapper.map(savedtodo, todoDto.class);


     return savedtodoDto;


    }

    @Override
    public todoDto getTodo(Long id) {

        todos todo = todoRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Todo not found for id " + id)
        );

      return modelMapper.map(todo,todoDto.class);
    }

    @Override
    public List<todoDto> getAlltodo() {
        List<todos> todoss = todoRepository.findAll();

        return todoss.stream().map((todo) -> modelMapper.map(todo,todoDto.class)).collect(Collectors.toList());
    }

    @Override
    public todoDto UpdateTodo(todoDto todoDto, Long id) {

        todos todo = todoRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException("not found ")
        );

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.getCompleted());

        todos updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, com.spring_todo.todo.dto.todoDto.class);

    }

    @Override
    public String DeleteTodo(Long id) {

        todoRepository.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public todoDto Compelete(Long id) {

        todos todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("not found"));

        todo.setCompleted(Boolean.TRUE);

        todos todoupdated = todoRepository.save(todo);

        return modelMapper.map(todoupdated,todoDto.class);

    }


}
