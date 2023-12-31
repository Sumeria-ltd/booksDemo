package com.sumeria.booksDemo.booksDemo.services;

import com.sumeria.booksDemo.booksDemo.models.Book;

import java.util.List;

public interface BooksService {
    List<Book> findAll();

    Book createBook(Book book);

    Book getBookByTitle(String title);

    Book getBookByAuthor(String author);

    boolean deleteBookByTitle(String title);
}
