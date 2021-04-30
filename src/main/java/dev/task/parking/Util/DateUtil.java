package dev.task.parking.Util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String formatDate(LocalDateTime time) {
        return time != null ? time.format(DateTimeFormatter.ofPattern("HH:mm")) : " NO REGISTRADO ";
    }

    public static long formatTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            return format.parse(time).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double formatDouble(double d) {
        DecimalFormat df=new DecimalFormat("#.##");
        return Double.parseDouble(df.format(d));
    }
}
