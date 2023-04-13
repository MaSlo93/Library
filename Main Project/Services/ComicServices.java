package Services;

import Pojo.Comic;

import java.util.Objects;

import Library.Library;

public class ComicServices implements LibraryServices {
    private Library library;

    @Override
    public void borrow(String name, int amount) {
        Comic comicToBorrow = retrieveLiterature(name);
        if (comicToBorrow.getAmount() == 0) {
            System.out.println("Sorry, we don't have any copies left.");
        } else if (comicToBorrow.getAmount() < amount) {
            System.out.println("Sorry, we don't have that many copies. We only have " + comicToBorrow.getAmount()
                    + " copies left");
        } else {
            comicToBorrow.setAmount(comicToBorrow.getAmount() - amount);
            updateLiterature(comicToBorrow);
        }
    }

    @Override
    public void returnLit(String name, int amount) {
        Comic comicToReturn = retrieveLiterature(name);
        comicToReturn.setAmount(comicToReturn.getAmount() + 1);
        updateLiterature(comicToReturn);
    }

    public ComicServices(Library library) {
        this.library = library;
    }

    public void addLiterature(Comic comic) {
        this.library.addLiterature(comic);
    }

    public Comic retrieveLiterature(String name) {
        return (Comic) this.library.retrieveLiterature(name);
    }

    public void updateLiterature(Comic comic) {
        this.library.updateLiterature(comic);
    }

    public void deleteLiterature(Comic comic) {
        this.library.deleteLiterature(comic);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ComicServices)) {
            return false;
        }
        ComicServices comicServices = (ComicServices) o;
        return Objects.equals(library, comicServices.library);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(library);
    }

}
