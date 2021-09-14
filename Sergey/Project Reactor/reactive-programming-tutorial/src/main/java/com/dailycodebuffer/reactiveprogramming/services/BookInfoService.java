package com.dailycodebuffer.reactiveprogramming.services;

import com.dailycodebuffer.reactiveprogramming.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getBooks() {
        var books = List.of(
                new BookInfo(1, "Book One", "Author One", "12121212121"),
                new BookInfo(2, "Book Two", "Author Two", "2323232323"),
                new BookInfo(3, "Book Three", "Author Three", "34343434343")
        );

        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId) {
        var book = new BookInfo(bookId, "Book One", "Author One", "2332323232323");

        return Mono.just(book);
    }
}
