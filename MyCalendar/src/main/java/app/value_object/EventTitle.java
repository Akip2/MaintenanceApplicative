package app.value_object;

public class EventTitle {
    private String label;

    public EventTitle(String input) {
        if (input == null || input.isEmpty()) {
            this.label = "default";
        } else {
            this.label = input;
        }
    }

    public String getValue() {
        return label;
    }
}
