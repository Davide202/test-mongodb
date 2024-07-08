package com.example.mongodbtests.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeTests {


    @Test
    void init(){
        String res = buildDateInStringFromEpoch("20230724","20230724");
        System.out.println(res);

        res = buildDateInStringFromEpoch("20230724","000000");
        System.out.println("res: " + res);
    }


    public static String buildDateInStringFromEpoch(String dateEpoch, String timeEpoch) {
        try {
            String dateTimeString = dateEpoch.concat(timeEpoch);
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = format.parse(dateTimeString);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "";
        }
    }
}
