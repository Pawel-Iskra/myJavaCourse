import hibernate.HibernateDao;
import model.Author;
import model.Book;
import service.LibraryService;

import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {

        LibraryService hibernateService = new LibraryService(new HibernateDao());

        Author author1 = new Author();
        author1.setFirstName("Andrzej");
        author1.setLastName("Sapkowski");

        hibernateService.addAuthor(author1);
        System.out.println("Pobieranie autora po nazwisku -> Sapkowski:");
        Author authorX = hibernateService.getAuthorByLastName("Sapkowski");
        System.out.println(String.format("%s %s, id = %d", authorX.getFirstName(), authorX.getLastName(), authorX.getId()));


        Book book1 = new Book();
        book1.setAuthor(hibernateService.getAuthorByLastName("Sapkowski"));
        book1.setTitle("Wiedźmin");
        book1.setDateOfRelease(new Date(95,02,15));
        book1.setPages(250);
        book1.setBookshelf("15/C");

        Book book2 = new Book();
        book2.setAuthor(hibernateService.getAuthorByLastName("Sapkowski"));
        book2.setTitle("Wiedźmin 2");
        book2.setDateOfRelease(new Date(97,04,20));
        book2.setPages(304);
        book2.setBookshelf("15/C");

        hibernateService.addBook(book1);
        hibernateService.addBook(book2);

        System.out.println("\nPobieranie książki po tytule -> Weidźmin 2:");
        Book bookX = hibernateService.getBookByTitle("Wiedźmin 2");
        System.out.println(String.format("Tytuł: %s, Autor: %s %s, stron = %d, półka = %s, data_wyadania = %s, id = %d",
                bookX.getTitle(), bookX.getAuthor().getFirstName(), bookX.getAuthor().getLastName(), bookX.getPages(),
                bookX.getBookshelf(), bookX.getDateOfRelease(), bookX.getId()));

        System.out.println("\nPobieranie wszystkich książek danego autora po nazwisku -> Sapkowski:");
        List<Book> books = hibernateService.getBooksByAuthorLastName("Sapkowski");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println(String.format("%d. %s", (i + 1), book.getTitle()));
        }



    }
}
