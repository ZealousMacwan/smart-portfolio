package studio.zealous.smartportfolio.util.time;

import org.springframework.stereotype.Component;
import studio.zealous.smartportfolio.config.properties.TimeZoneProperties;

import java.time.LocalDateTime;

@Component
public class TimeUtil {

    private static TimeZoneProperties timeZoneProperties;

    TimeUtil(TimeZoneProperties timeZoneProperties){
        TimeUtil.timeZoneProperties = timeZoneProperties;
    }

    public static LocalDateTime nowWithZone(){
        return LocalDateTime.now(timeZoneProperties.getZoneid());
    }

    public static LocalDateTime nowWithoutZone(){
        return LocalDateTime.now();
    }

}
