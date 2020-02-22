package service;

import dao.Dao;
import model.Author;
import model.Book;

import java.util.List;

public class LibraryService {

    private Dao libraryDao;

    public LibraryService(Dao dao) {
        this.libraryDao = dao;
    }

    public void addBook(Book book) {
        libraryDao.addBook(book);
    }

    public List<Book> getBooksByAuthorLastName(String authorLastName) {
        return libraryDao.getBooksByAuthorLastName(authorLastName);
    }

    public Book getBookById(int id) {
        return libraryDao.getBookById(id);
    }

    public Book getBookByTitle(String title) {
        return libraryDao.getBookByTitle(title);
    }

    public Book deleteBookById(int id) {
        return libraryDao.deleteBookById(id);
    }

    public Book updateBook(Book book) {
        return libraryDao.updateBook(book);
    }

    public void addAuthor(Author author) {
        libraryDao.addAuthor(author);
    }

    public Author getAuthorById(int id) {
        return libraryDao.getAuthorById(id);
    }

    public Author getAuthorByLastName(String lastName) {
        return libraryDao.getAuthorByLastName(lastName);
    }

    public Author deleteAuthorById(int id) {
        return libraryDao.deleteAuthorById(id);
    }

    public Author updateAuthor(Author author) {
        return libraryDao.updateAuthor(author);
    }

}
