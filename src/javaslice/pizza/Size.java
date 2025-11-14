
package javaslice.pizza;

/**
 * Size
 * Enumerates the available sizes of pizzas
 * 
 * @author Andrian
 */
public enum Size {
    S("Small", "S"),
    M("Medium", "M"),
    L("Large", "L");

    private final String name; // name of the size
    private final String abbr; // abbreviation of the size

    Size(String name, String abbr) {
        this.name = name;
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public String getAbbr() {
        return abbr;
    }

    // factory method
    public static Size of(String nameOrAbbr) {
        String input = nameOrAbbr.trim().toLowerCase();
        for (Size size : values()) {
            if (size.name.toLowerCase().equals(input) || size.abbr.toLowerCase().equals(input)) {
                return size;
            }
        }
        throw new IllegalArgumentException("Unknown size: " + nameOrAbbr);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, abbr);
    }
}
