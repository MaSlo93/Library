package Services;

import Pojo.Book;

import java.util.Objects;

import Library.Library;

public class BookServices implements LibraryServices {
    private Library library;

    @Override
    public void borrow(String name, int amount) {
        Book bookToBorrow = retrieveLiterature(name);
        if (bookToBorrow.getAmount() == 0) {
            System.out.println("Sorry, we don't have any copies left.");
        } else if (bookToBorrow.getAmount() < amount) {
            System.out.println(
                    "Sorry, we don't have that many copies. We only have " + bookToBorrow.getAmount() + " copies left");
        } else {
            bookToBorrow.setAmount(bookToBorrow.getAmount() - amount);
            updateLiterature(bookToBorrow);
        }
    }

    @Override
    public void returnLit(String name, int amount) {
        Book bookToReturn = retrieveLiterature(name);
        bookToReturn.setAmount(bookToReturn.getAmount() + amount);
        updateLiterature(bookToReturn);
    }

    public BookServices(Library library) {
        this.library = library;
    }

    public void addLiterature(Book book) {
        this.library.addLiterature(book);
    }

    public Book retrieveLiterature(String name) {
        return (Book) this.library.retrieveLiterature(name);
    }

    public void updateLiterature(Book book) {
        this.library.updateLiterature(book);
    }

    public void deleteLiterature(Book book) {
        this.library.deleteLiterature(book);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BookServices)) {
            return false;
        }
        BookServices bookServices = (BookServices) o;
        return Objects.equals(library, bookServices.library);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(library);
    }

}
