package me.shuza.rxdatabinding;

/**
 * Created by shuza on 9/26/17.
 */

public class NumberUtil {
    public static float getFloat(String message) {
        try {
            return Float.parseFloat(message);
        } catch (Exception e) {
            return 0;
        }
    }
}
