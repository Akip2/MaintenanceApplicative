package app.value_object;

public class EventDuration {
    private int minutes;

    public EventDuration(String m) {
        try {
            int mInt = Integer.parseInt(m);
            this.minutes = Math.max(mInt, 1);
        } catch (NumberFormatException e) {
            this.minutes = 1;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(minutes);
    }

    public int getValue() {
        return minutes;
    }
}
