package org.example;

import org.example.entity.Author;
import org.example.entity.Book;

public class TestFixture {

    public static Book buildBook(){
        Book book = new Book();
        book.setAuthor(buildAuthor());
        book.setTitle("History of a city");
        book.setIsbn("001-JN");
        return book;
    }

    public static Author buildAuthor(){
        Author author = new Author();
        author.setName("John");
        author.setAge(32);
        author.setGenre("History");
        return author;
    }

}
