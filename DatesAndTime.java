package DatesAndTime;





import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesAndTime {

    // Create a Date object representing the current date and time
    static Date currentDate = new Date();

    // Create a SimpleDateFormat object for 12-hour time format with AM/PM
    static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

    // Format the current date and time
    static String formattedDate = dateFormat.format(currentDate);

    // Display the formatted date and time

    public static Date getCurrentDate() {
        java.util.Date utilDate = new java.util.Date();
        long milliseconds = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(milliseconds);
          return sqlDate;
    }

    static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public static Time getFormattedDate() {
        return Time.valueOf(formattedDate);
    }

    @Override
    public String toString() {
        return "DatesAndTime{" +
                "currentDate=" + currentDate +
                ", dateFormat=" + dateFormat +
                ", formattedDate='" + formattedDate + '\'' +
                '}';
    }
}


