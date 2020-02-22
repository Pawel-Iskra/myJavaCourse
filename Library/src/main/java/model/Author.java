package model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    @Size(min = 2, max = 256, message = "first name must be longer than 2 and shorter than 256")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "fill in with letters and space only")
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 256, message = "last name must be longer than 2 and shorter than 256")
    @Pattern(regexp = "^[a-zA-Z- ]*$", message = "fill in with letters, '-' and space only")
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
