package value_object;

public class EventTitle {
    private final String title;

    public EventTitle(String input) {
        if (input == null || input.isEmpty()) {
            this.title = "default";
        } else {
            this.title = input;
        }
    }

    @Override
    public String toString() {
        return this.title;
    }

    public String getValue() {
        return title;
    }
}
