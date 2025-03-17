package value_object;

public class MonthStart {
    private int month;

    public MonthStart(String input) {
        try {
            int dInt = Integer.parseInt(input);

            if(dInt > 12) {
                this.month = 12;
            } else this.month = Math.max(dInt, 0);
        } catch (NumberFormatException e) {
            this.month = 0;
        }
    }

    public int getValue() {
        return month;
    }
}
