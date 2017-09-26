package me.shuza.rxtimerdemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shuza on 9/26/17.
 */

public class TimeUtil {
    public static SimpleDateFormat getTimeFormat() {
        return new SimpleDateFormat("hh:mm:ss");
    }

    public static String getCurrentTime() {
        Date currentDate = Calendar.getInstance().getTime();
        return getTimeFormat().format(currentDate);
    }
}
