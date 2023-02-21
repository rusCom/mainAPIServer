package tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public class DateTimeUtils {

    public static LocalDateTime convertFromCache(String cacheDate) {
        if (cacheDate.equals(""))return null;
        if (cacheDate.equals("0"))return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(cacheDate, formatter);
    }

    public static String convertToCache(LocalDateTime date){
        String res = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(date);
        return res;
    }

    public static Integer passedTimeDay(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long timeout = zonedDateTime.toInstant().toEpochMilli();
        if (timeout == 0) {
            return 2147483647;
        }
        long absMillis = Math.abs(System.currentTimeMillis() - timeout);
        int absSek = Math.toIntExact(absMillis / 1000);
        int absMinute = Math.toIntExact(absSek / 60);
        int absHour = Math.toIntExact(absMinute / 60);
        int absDay = Math.toIntExact(absHour / 24);

        return absDay;
    }
}
