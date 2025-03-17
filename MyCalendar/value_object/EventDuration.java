package value_object;

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

    public int getValue() {
        return minutes;
    }
}
