package com.example.simple_resful.controllers;

import com.example.simple_resful.exception.BookIdMismatchException;
import com.example.simple_resful.exception.BookNotFoundException;
import com.example.simple_resful.models.Book;
import com.example.simple_resful.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable<Book> findAll() {
        logger.info("### findAll");
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List<Book> findByTitle(@PathVariable String bookTitle) {
        logger.info("### findByTitle");
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable long id) {
        logger.info("### findOne");
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw  new BookNotFoundException();
        } else {
            return book;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        logger.info("### create");
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        logger.info("### delete");
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw  new BookNotFoundException();
        } else {
        }
        bookRepository.delete(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable long id) {
        logger.info("### updateBook");
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        Book bookRepositoryOne = bookRepository.findOne(id);
        if (bookRepositoryOne == null) {
            throw  new BookNotFoundException();
        }

        return bookRepository.save(book);
    }
}
