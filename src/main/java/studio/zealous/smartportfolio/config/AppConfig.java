package studio.zealous.smartportfolio.config;

import com.angelbroking.smartapi.SmartConnect;
/*import com.angelbroking.smartapi.http.SessionExpiryHook;*/
import com.angelbroking.smartapi.models.User;
/*import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import lombok.Value;*/
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.data.mongodb.core.MongoClientFactoryBean;*/
import studio.zealous.smartportfolio.config.properties.AngelOneProperties;
import studio.zealous.smartportfolio.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Configuration
@ConfigurationPropertiesScan
public class AppConfig {



    @Bean
    @ConfigurationProperties(prefix = "angelone")
    public AngelOneProperties angelOneProperties() {
        return new AngelOneProperties();
    }


    @Bean
    public SmartConnect smartConnect(AngelOneProperties angelOneProperties){
        SmartConnect smartConnect = new SmartConnect();
        smartConnect.setApiKey(angelOneProperties.getTradingapikey());

        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter TOTP code: ");
        //Integer totp = scanner.nextInt();
        //User user = smartConnect.generateSession(angelOneProperties().getClientcode(), angelOneProperties().getClientpassword(), String.valueOf(totp));
        //smartConnect.setAccessToken(user.getAccessToken());
        //smartConnect.setUserId(user.getUserId());

        return smartConnect;
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        objectMapper.registerModule(module);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

/*    @Bean
    public MongoClientFactoryBean mongo(@Value("${custom.uri}") String uri) {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        ConnectionString conn = new ConnectionString(uri);
        mongo.setConnectionString(conn);
        return mongo;
    }*/
}
