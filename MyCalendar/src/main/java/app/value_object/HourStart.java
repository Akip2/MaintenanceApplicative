package app.value_object;

public class HourStart {
    private int hours;

    public HourStart(String input) {
        try {
            int hInt = Integer.parseInt(input);

            if(hInt > 23) {
                this.hours = 23;
            } else this.hours = Math.max(hInt, 0);
        } catch (NumberFormatException e) {
            this.hours = 0;
        }
    }

    public int getValue() {
        return hours;
    }
}
