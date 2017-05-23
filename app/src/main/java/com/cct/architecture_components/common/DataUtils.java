package com.cct.architecture_components.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Carlos Carrasco Torres on 22/05/2017.
 */

public class DataUtils {
    public static String trimYearDate(String fullDate) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date yourDate = parser.parse(fullDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(yourDate);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }
}
