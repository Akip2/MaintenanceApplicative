package app.value_object;

public class EventFrequency {
    private int frequencyDay;

    public EventFrequency(String input) {
        try {
            int fInt = Integer.parseInt(input);

            frequencyDay =Math.max(fInt, 0);
        } catch (NumberFormatException e) {
            this.frequencyDay = 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EventFrequency) {
            return ((EventFrequency)obj).getValue() == frequencyDay;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.frequencyDay);
    }

    public int getValue() {
        return frequencyDay;
    }
}
