package com.hodu.crud.service;

import com.hodu.crud.entity.Book;
import com.hodu.crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book Book) {
        try {
            this.bookRepository.save(Book);
        } catch (Exception e) {
            throw new RuntimeException("save error.");
        }
        return Book;
    }

    @Override
    public Book getBookById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book Book = null;
        if (optionalBook.isPresent()) {
            Book = optionalBook.get();
        } else {
            throw new RuntimeException("Book not found for id : " + id);
        }
        return Book;
    }

    @Override
    public Book updateBook(long id, Book Book) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book _bookData = null;
        if (optionalBook.isPresent()) {
            _bookData.setBookName(Book.getBookName());
            _bookData.setAuthor(Book.getAuthor());
        } else {
            throw new RuntimeException("Book not found for id : " + id);
        }
        return _bookData;
    }

    @Override
    public void deleteBookById(long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.bookRepository.findAll(pageable);
    }
}
