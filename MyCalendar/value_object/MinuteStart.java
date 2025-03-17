package value_object;

public class MinuteStart {
    private int minutes;

    public MinuteStart(String input) {
        try {
            int mInt = Integer.parseInt(input);
            this.minutes = Math.max(mInt, 0);
        } catch (NumberFormatException e) {
            this.minutes = 0;
        }
    }

    public int getValue() {
        return minutes;
    }
}
