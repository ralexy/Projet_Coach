package com.example.alexy.coach.outils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class MesOutils {
    /**
     * Conversion chaine sous format : Tues Feb 07 09:16:17 GMT 2018 (EEE MMM dd hh:mm:ss 'CET' yyyy) vers date
     * @param uneDate
     * @return
     */
    public static Date convertStringToDate(String uneDate) {
        String expectedPattern = "EEE MMM dd hh:mm:ss 'CET' yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern, Locale.US);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateToString(Date uneDate) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(uneDate);
    }
}
