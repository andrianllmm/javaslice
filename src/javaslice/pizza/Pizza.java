package javaslice.pizza;

import static javaslice.Config.*;

import java.util.Objects;

/**
 * Pizza
 * Represents a pizza with a name and size
 * 
 * @author Andrian
 */
public abstract class Pizza {
    protected String name;
    protected Size size;

    public Pizza(String name, Size size) {
        this.name = name;
        this.size = size;
    }

    public Pizza(String name) {
        this.name = name;
        this.size = DEFAULT_SIZE;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public abstract double getPrice();

    @Override
    public String toString() {
        return String.format("%s (%s)", name, size.getAbbr());
    }

    // pizzas are equal if they have the same name and size
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pizza))
            return false;
        Pizza that = (Pizza) o;
        return name.equals(that.getName()) && size.equals(that.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
