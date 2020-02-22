package dao;

import model.Author;
import model.Book;

import java.util.List;

public interface Dao {

    void addBook(Book book);
    List<Book> getBooksByAuthorLastName(String authorLastName);
    Book getBookById (int id);
    Book getBookByTitle (String title);
    Book deleteBookById(int id);
    Book updateBook(Book book);

    void addAuthor(Author author);
    Author getAuthorById(int id);
    Author getAuthorByLastName(String lastName);
    Author deleteAuthorById(int id);
    Author updateAuthor(Author author);
}
