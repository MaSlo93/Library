package Pojo;

import java.util.Objects;

public class Comic extends Literature {
    int volume;

    public Comic(int amount, int volume, String name, String author, String genre, int yearPrint) {
        super(amount, name, author, genre, yearPrint);
        this.volume = volume;
    }

    public Comic(Comic source) {
        super(source.amount, source.name, source.author, source.genre, source.yearPrint);
        this.volume = source.volume;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\tVolume: " + getVolume();
    }

    @Override
    public Literature clone() {

        return new Comic(amount, volume, name, author, genre, yearPrint);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Comic)) {
            return false;
        }
        Comic comic = (Comic) o;
        return volume == comic.volume;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(volume);
    }

}
