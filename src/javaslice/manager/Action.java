package javaslice.manager;

/**
 * Action
 * Enumerates the actions the user can take
 * 
 * @author Andrian
 */
public enum Action {
    VIEW_MENU("m", "View Menu"),
    ADD("a", "Add item"),
    REMOVE("r", "Remove item"),
    VIEW_CART("c", "View Cart"),
    CHECKOUT("o", "Checkout"),
    QUIT("q", "Quit");

    private final String code; // code of the action
    private final String name; // name of the action

    Action(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    // factory method
    public static Action of(String code) throws IllegalArgumentException {
        for (Action action : values()) {
            if (action.getCode().equalsIgnoreCase(code)) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }

    @Override
    public String toString() {
        return getName();
    }
}
