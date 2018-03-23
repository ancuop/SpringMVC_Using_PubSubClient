package com.example.simple_resful.repository;

import com.example.simple_resful.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>{
    List<Book> findByTitle(String title);
    Book findOne(Long id);
}
