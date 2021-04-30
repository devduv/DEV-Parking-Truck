package dev.task.parking.domain.time;

public class TimeBuilder {
    private String inTime;
    private String outTime;

    public TimeBuilder inTime(String inTime) {
        this.inTime = inTime;
        return this;
    }

    public TimeBuilder outTime(String outTime) {
        this.outTime = outTime;
        return this;
    }

    public Time build() {
        return new Time(inTime, outTime);
    }
}