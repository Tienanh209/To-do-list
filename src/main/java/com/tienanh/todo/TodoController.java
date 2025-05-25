package com.tienanh.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public Iterable<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo createToDo(@RequestParam String title, @RequestParam String description, @RequestParam boolean completed) {
        Todo todo = new Todo(title, description, completed);
        return todoRepository.save(todo);
    }

    @GetMapping("/{id}")
    public Todo getToDoById(@PathVariable Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ToDo not found"));
    }

    @PutMapping("/{id}")
    public Todo updateToDo(@PathVariable Long id, @RequestParam String title, @RequestParam String description, @RequestParam boolean completed) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ToDo not found"));
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(completed);
        return todoRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}
