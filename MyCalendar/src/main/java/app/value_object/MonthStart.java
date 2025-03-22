package app.value_object;

public class MonthStart {
    private int month;

    public MonthStart(String input) {
        try {
            int dInt = Integer.parseInt(input);

            if(dInt > 12) {
                this.month = 12;
            } else this.month = Math.max(dInt, 1);
        } catch (NumberFormatException e) {
            this.month = 1;
        }
    }

    public int getValue() {
        return month;
    }
}
