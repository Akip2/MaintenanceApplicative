package app.value_object;

public class EventPlace {
    private String place;

    public EventPlace(String input) {
        if (input == null || input.isEmpty()) {
            this.place = "default";
        } else {
            this.place = input;
        }
    }

    @Override
    public String toString() {
        return place;
    }

    public String getValue() {
        return place;
    }
}
