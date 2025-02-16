package classes;

import java.util.Calendar;
import java.util.Date;

public class DateApp {
  public static void main(String[] args) {

    Date tanggal = new Date();
    System.out.println(tanggal);

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 1996);
    calendar.set(Calendar.MONTH, Calendar.AUGUST);
    calendar.set(Calendar.DAY_OF_MONTH, 10);
    calendar.set(Calendar.HOUR_OF_DAY, 10);

    Date result = calendar.getTime();

    System.out.println(result);
  }
}
