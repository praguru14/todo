package com.pg.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository; // Assuming you have a TodoRepository for database operations

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<?> addTodo(@RequestBody Todo todo) {
        try {
            // Save the todo task to the database or perform any other necessary operations
            todoRepository.save(todo);
            return ResponseEntity.ok().build(); // Return success response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return error response
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long id) {
        try {
            // Check if the todo item exists in the database
            Optional<Todo> todoOptional = todoRepository.findById(id);
            if (todoOptional.isPresent()) {
                // Delete the todo item from the database
                todoRepository.deleteById(id);
                return ResponseEntity.ok().build(); // Return success response
            } else {
                return ResponseEntity.notFound().build(); // Return not found response if todo item doesn't exist
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return error response
        }
    }
}
