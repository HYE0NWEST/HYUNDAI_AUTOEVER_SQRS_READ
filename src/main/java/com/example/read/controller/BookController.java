package com.example.read.controller;

import com.example.read.entity.Book;
import com.example.read.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Read Service is running on port 8081");
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("success");
    }

    @GetMapping("/cqrs/book")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }
}
