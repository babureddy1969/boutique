package com.example.boutique.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static String getDateTime(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
        try {
            Date d = sdf.parse(timestamp);
            sdf = new SimpleDateFormat("dd-MMM-yy hh:mm a");
            return sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
            return timestamp;
        }
    }
}
