package com.sumeria.booksDemo.booksDemo.controllers;

import com.sumeria.booksDemo.booksDemo.Dtos.DeleteBookResposnse;
import com.sumeria.booksDemo.booksDemo.models.Book;
import com.sumeria.booksDemo.booksDemo.Dtos.BookRequest;
import com.sumeria.booksDemo.booksDemo.services.BooksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@RequiredArgsConstructor
public class BooksController {

    private final BooksService booksService;

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("location", "Arab Bank")
                .body(booksService.findAll());
    }

    @GetMapping("/{title}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        Book result = booksService.getBookByTitle(title);
        if(result == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("location", "Arab Bank")
                    .body(null);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("location", "Arab Bank")
                .body(result);
    }

    @GetMapping(params = {"author"}) // TODO explain why params is needed
    public ResponseEntity<Book> getBookByAuthor(@RequestParam String author) {
        Book result = booksService.getBookByAuthor(author);
        if(result == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("location", "Arab Bank")
                    .body(null);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("location", "Arab Bank")
                .body(result);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<DeleteBookResposnse> deleteByTitle(@PathVariable String title) {
        boolean result = booksService.deleteBookByTitle(title);
        if(result) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new DeleteBookResposnse(title));
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(
            @RequestBody
            @Valid
            BookRequest book){
        if("Firas".equals(book.getAuthor())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Firas is no good");
        }

        if("Something".equals(book.getSomeThingWeDontNeedInModel())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "This Field cannot be Something");
        }
        return booksService.createBook(BookRequest.toModel(book));
    }
}
