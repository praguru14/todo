package com.pg.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private PassRepo passRepo;
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<?> addTodo(@RequestBody Todo todo) {
        try {
            todoRepository.save(todo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long id) {
        try {
            Optional<Todo> todoOptional = todoRepository.findById(id);
            if (todoOptional.isPresent()) {
                todoRepository.deleteById(id);
                return ResponseEntity.ok().build(); // Return success response
            } else {
                return ResponseEntity.notFound().build();    }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return error response
        }
    }

    @PostMapping("/pass")
    public ResponseEntity<String> verifyPassword(@RequestBody Map<String,String> password) {
        String fromDb = passRepo.findPasswordByPassword();
        String pass = password.get("password");
        System.out.println(fromDb + "   "+pass);
        if (pass.equals(fromDb)) {
            return ResponseEntity.ok("Password verified.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password.");

        }
    }
}
