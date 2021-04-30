package dev.task.parking.domain.time;

public class Time {
    private final String inTime;
    private String outTime;

    public Time(String inTime, String outTime) {
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String getInTime() {
        return inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public static TimeBuilder builder(){
        return new TimeBuilder();
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
