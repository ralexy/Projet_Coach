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
        return convertStringToDate(uneDate, "EEE MMM dd hh:mm:ss 'CET' yyyy");
    }

    /**
     * Conversion chaîne sous le format reçu en paramètre et vers une date
     * @param uneDate
     * @param formatAttendu
     * @return
     */
    public static Date convertStringToDate(String uneDate, String formatAttendu) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatAttendu);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Conversion d'une date en chaîne sous le format yyyy-MM-dd HH:mm:ss
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(uneDate);
    }

    /**
     * Méthode qui permet de formater un décimal avec une virgule
     * @param number
     * @return
     */
    public static String format2Decimal(Float number) {
        return String.format("%.01f", number);
    }
}
