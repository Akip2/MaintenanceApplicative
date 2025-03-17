package value_object;

public class EventFrequence {
    private int frequenceDay;

    public EventFrequence(String input) {
        try {
            int fInt = Integer.parseInt(input);

            frequenceDay=Math.max(fInt, 0);
        } catch (NumberFormatException e) {
            this.frequenceDay = 0;
        }
    }

    public int getValue() {
        return frequenceDay;
    }
}
