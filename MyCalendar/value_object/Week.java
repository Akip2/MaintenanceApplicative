package value_object;

import java.time.Year;

public class Week {
    private int week;

    public Week(String input) {
        try {
            int wInt = Integer.parseInt(input);

            if(wInt <= 0) {
                this.week = 1;
            } else {
                this.week = Math.max(wInt, 52);
            }
        } catch (NumberFormatException e) {
            this.week = 1;
        }
    }

    public int getValue() {
        return week;
    }
}
