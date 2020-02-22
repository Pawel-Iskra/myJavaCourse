package hibernate;

import dao.Dao;
import model.Author;
import model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateDao implements Dao {


    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public HibernateDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("library");
    }

    private void beginTransaction() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    private void commitTransaction() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void addBook(Book book) {
        beginTransaction();
        entityManager.merge(book);
        commitTransaction();
    }

    @Override
    public List<Book> getBooksByAuthorLastName(String authorLastName) {
        String sql1 = "SELECT * FROM AUTHOR WHERE LASTNAME =:authorLastName";
        beginTransaction();
        Author author = (Author) entityManager.createNativeQuery(sql1,Author.class)
                .setParameter("authorLastName",authorLastName).getSingleResult();
        Long id = author.getId();
        String sql2 = "SELECT * FROM BOOK WHERE AUTHOR_ID =:authorId";
        List<Book> books = entityManager.createNativeQuery(sql2, Book.class)
                .setParameter("authorId",id).getResultList();
        commitTransaction();
        return books;
    }

    @Override
    public Book getBookById(int id) {
        beginTransaction();
        Book book = entityManager.find(Book.class, id);
        commitTransaction();
        return book;
    }

    @Override
    public Book getBookByTitle(String title) {
        String sql = "SELECT * FROM BOOK WHERE TITLE =:bookTitle";
        beginTransaction();
        Book book = (Book) entityManager.createNativeQuery(sql, Book.class)
                .setParameter("bookTitle",title).getSingleResult();
        commitTransaction();
        return book;
    }

    @Override
    public Book deleteBookById(int id) {
        beginTransaction();
        Book bookToDelete = entityManager.find(Book.class, id);
        entityManager.remove(bookToDelete);
        commitTransaction();
        return bookToDelete;
    }

    @Override
    public Book updateBook(Book book) {
        Long id = book.getId();
        beginTransaction();
        entityManager.merge(book);
        Book bookUpdated = entityManager.find(Book.class, id);
        commitTransaction();
        return bookUpdated;
    }

    @Override
    public void addAuthor(Author author) {
        beginTransaction();
        entityManager.merge(author);
        commitTransaction();
    }

    @Override
    public Author getAuthorById(int id) {
        beginTransaction();
        Author author = entityManager.find(Author.class, id);
        commitTransaction();
        return author;
    }

    @Override
    public Author getAuthorByLastName(String lastName) {
        String sql = "SELECT * FROM AUTHOR WHERE LASTNAME =:authorLastName";
        beginTransaction();
        Author author = (Author) entityManager.createNativeQuery(sql,Author.class)
                .setParameter("authorLastName",lastName).getSingleResult();
        commitTransaction();
        return author;
    }

    @Override
    public Author deleteAuthorById(int id) {
        beginTransaction();
        Author authorToDelete = entityManager.find(Author.class, id);
        entityManager.remove(authorToDelete);
        commitTransaction();
        return authorToDelete;
    }

    @Override
    public Author updateAuthor(Author author) {
        Long id = author.getId();
        beginTransaction();
        entityManager.merge(author);
        Author authorUpdated = entityManager.find(Author.class, id);
        commitTransaction();
        return authorUpdated;
    }
}
