package com.spring_todo.todo.repository;

import com.spring_todo.todo.entity.todos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface todoRepository extends JpaRepository<todos,Long> {
}
