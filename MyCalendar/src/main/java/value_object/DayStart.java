package value_object;

public class DayStart {
    private int day;

    public DayStart(String input) {
        try {
            int dInt = Integer.parseInt(input);

            if(dInt > 31) {
                this.day = 31;
            } else this.day = Math.max(dInt, 0);
        } catch (NumberFormatException e) {
            this.day = 0;
        }
    }

    public int getValue() {
        return day;
    }
}
