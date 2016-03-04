package src;
import java.util.*;
import java.text.*;

public class DateAndTime {
   public static void main(String args[]) {

      Date DandT = new Date( );
      SimpleDateFormat DayOfWeek = new SimpleDateFormat ("E");
      String sDate = DayOfWeek.format(DandT);
      System.out.println("Current Date: " + sDate);
      SimpleDateFormat CurrentTime = new SimpleDateFormat ("kkmm");
      String iTime = CurrentTime.format(DandT);
      System.out.println("Current Time: " + iTime);
      int d;
      switch (sDate){
      case "Sun": d = 0;
      break;
      case "Mon": d = 1;
      break;
      case "Tue": d = 2;
      break;
      case "Wed": d = 3;
      break;
      case "Thu": d = 4;
      break;
      case "Fri": d = 5;
      break;
      case "Sat": d = 6;
      break;
      }
      
   }
}