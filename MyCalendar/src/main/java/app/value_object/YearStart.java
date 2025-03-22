package app.value_object;

import java.time.Year;

public class YearStart {
    private int year;

    public YearStart(String input) {
        int currentYear = Year.now().getValue();
        try {
            int yInt = Integer.parseInt(input);
            this.year = Math.max(yInt, currentYear);
        } catch (NumberFormatException e) {
            this.year = currentYear;
        }
    }

    public int getValue() {
        return year;
    }
}
