package Pojo;

import java.util.Objects;

public class Book extends Literature {
    String isbn;

    public Book(int amount, String isbn, String name, String author, String genre, int yearPrint) {
        super(amount, name, author, genre, yearPrint);
        this.isbn = isbn;
    }

    public Book(Book source) {
        super(source.amount, source.name, source.author, source.genre, source.yearPrint);
        this.isbn = source.isbn;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return super.toString() + "\tISBN: " + getIsbn();
    }

    @Override
    public Literature clone() {
        return new Book(amount, isbn, name, author, genre, yearPrint);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

}
