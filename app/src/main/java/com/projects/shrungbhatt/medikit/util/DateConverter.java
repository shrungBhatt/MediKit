package com.projects.shrungbhatt.medikit.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jigsaw on 9/2/18.
 */

public class DateConverter {


    //convert 12 hrs time.
    public static String TimeToString(String timeString) {

        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
            DateTime date = formatter.parseDateTime(timeString);
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("hh:mm a");
            return dtfOut.print(date);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //convert 24 hrs time.
    public static String Time24ToString(String timeString) {

        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("hh:mm a");
            DateTime date = formatter.parseDateTime(timeString);
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("HH:mm");
            return dtfOut.print(date);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //convert server to user side 12 hrs time..
    public static String TimeToStringConvert(String timeString) {

        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss.SSS");
            DateTime date = formatter.parseDateTime(timeString);
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("hh:mm a");
            return dtfOut.print(date);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //convert user to server side formate..24 hrs time
    public static String StringtoTimeConvert(String timestring) {
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("HH:mm");
        DateTime date = dtfOut.parseDateTime(timestring);
        DateTimeFormatter dateTime = DateTimeFormat.forPattern("HH:mm:ss.SSS");
        return dateTime.print(date);
    }

    //convert user to server side formate..12 hrs time
    public static String convertToServerTime1(String time) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("hh:mm a");
        DateTime date = formatter.parseDateTime(time);
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("HH:mm:ss.SSS");
        return dtfOut.print(date);

    }

    //convet  utc to simple formate...
    public static String convertDate(String OurDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(OurDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a"); //this format changeable
            dateFormatter.setTimeZone(TimeZone.getDefault());
            OurDate = dateFormatter.format(value);

            //Log.d("OurDate", OurDate);
        } catch (Exception e) {
            OurDate = "00-00-0000 00:00";
        }
        return OurDate;
    }

    //only yyyy-MM-dd convert to DD/MM/YYYY Formt
    public static String ShowDate(String Date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime date = formatter.parseDateTime(Date);
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("DD-MMM-YYYY");
        return dtfOut.print(date);
    }


    public static String convertDate1(String Date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(Date);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy"); //this format changeable
            dateFormatter.setTimeZone(TimeZone.getDefault());
            Date = dateFormatter.format(value);
        } catch (Exception e) {
            e.printStackTrace();
            Date = "00-00-0000 00:00";
        }
        return Date;
    }

    public static String convertDate2(String Date) {
        String formatDate = Date.replace('T', ' ');
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-dd-mm HH:mm:ss.SSS");
        DateTime date = formatter.parseDateTime(formatDate);
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("DD-MMM-YYYY");
        return dtfOut.print(date);
    }

    public static String convertToServerTime(String Date) {
        if (!Date.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-yyyy");
            DateTime date = formatter.parseDateTime(Date);
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd-MMM-yyyy hh:mm:ss ");
            return dtfOut.print(date);
        }
        return Date;
    }

    public static String convertServerToUserTime(String Date) {
        if (!Date.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-mm-yyyy hh:mm:ss ");
            DateTime date = formatter.parseDateTime(Date);
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyy-dd-mm");
            return dtfOut.print(date);
        }
        return Date;
    }


}
