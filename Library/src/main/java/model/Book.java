package model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    @Size(min = 3, max = 255, message = "title must be longer than 2 and shorter than 256")
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;
    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private Date dateOfRelease;
    @Min(value = 2, message = "number of pages must be >= 2")
    private int pages;
    @NotEmpty
    private String bookshelf;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }

}
