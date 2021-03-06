package datn.interfaces.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static String dateFormat = "dd/MM/yyyy";
    private static DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    public static boolean isFormatDate(String dateInputString) {
        simpleDateFormat.setLenient(false);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateInputString);
        }catch (ParseException e){
            return false;
        }
        return true;
    }

    public static boolean isFutureDate(String dateInputString){
        simpleDateFormat.setLenient(false);
        Date dateInput = null;
        Date currentDate = new Date();
        try{
            currentDate = simpleDateFormat.parse(currentDate.toString());
            dateInput = simpleDateFormat.parse(dateInputString);

        }catch (ParseException e) {
            return false;
        }
        return compareToDate(currentDate, dateInput);
    }

    private static boolean compareToDate (Date currentDate, Date inputDate){
        if(inputDate.compareTo(currentDate) == 1){
            return false;
        }
        return true;
    }

    public static Date convertStringToDate(String dateString) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDateToString(Date date){
        if(date == null)
            return "";
        return  simpleDateFormat.format(date);
    }

    public static String convertDateTimeToString(Date date){
        return dateTimeFormat.format(date);
    }

    public static boolean isDateInPeriodTime(Date date, Date startDate, Date endDate){
        if(date.compareTo(startDate)>=0 && date.compareTo(endDate)<=0){
            return true;
        }

        return false;
    }

}