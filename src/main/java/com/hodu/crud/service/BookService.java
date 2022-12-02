package com.hodu.crud.service;

import com.hodu.crud.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book saveBook(Book Book);
    Book getBookById(long id);

    Book updateBook(long id, Book Book);
    void deleteBookById(long id);
    Page<Book> findPaginated(int pageNum, int pageSize,
                               String sortField,
                               String sortDirection);
}
