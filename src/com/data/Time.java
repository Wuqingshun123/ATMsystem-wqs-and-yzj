package data;

import java.util.Calendar;
import java.io.*;
import java.util.GregorianCalendar;


public class Time {
    public static String gettime(){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int min = now.get(Calendar.MINUTE);
        int s = now.get(Calendar.SECOND);
        StringBuffer str = new StringBuffer();
        str.append("[");
        str.append(year + "-");
        str.append(String.format("%02d", month) + "-");
        str.append(String.format("%02d", day));
        str.append(" ");
        str.append(String.format("%02d", hour) + ":");
        str.append(String.format("%02d", min) + ":");
        str.append(String.format("%02d", s));
        str.append("]");
        return new String(str);
    }
}
