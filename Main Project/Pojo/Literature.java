package Pojo;

import java.util.Objects;

public abstract class Literature {
    int amount;
    String name;
    String author;
    String genre;
    int yearPrint;

    public Literature(int amount, String name, String author, String genre, int yearPrint) {
        this.amount = amount;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.yearPrint = yearPrint;
    }

    public Literature(Literature source) {
        this.amount = source.amount;
        this.name = source.name;
        this.author = source.author;
        this.genre = source.genre;
        this.yearPrint = source.yearPrint;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYearPrint() {
        return this.yearPrint;
    }

    public void setYearPrint(int yearPrint) {
        this.yearPrint = yearPrint;
    }

    @Override
    public String toString() {
        return "Amount availiable: " + getAmount() +
                "\tName: " + getName() +
                "\tAuthor: " + getAuthor() +
                "\tGenre: " + getGenre() +
                "\tYearPrint: " + getYearPrint();
    }

    @Override
    public abstract Literature clone();

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Literature)) {
            return false;
        }
        Literature literature = (Literature) o;
        return amount == literature.amount && Objects.equals(name, literature.name)
                && Objects.equals(author, literature.author) && Objects.equals(genre, literature.genre)
                && yearPrint == literature.yearPrint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, name, author, genre, yearPrint);
    }

}
