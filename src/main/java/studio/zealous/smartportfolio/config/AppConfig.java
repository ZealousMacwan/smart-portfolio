package studio.zealous.smartportfolio.config;

import com.angelbroking.smartapi.SmartConnect;
/*import com.angelbroking.smartapi.http.SessionExpiryHook;*/
import com.angelbroking.smartapi.models.User;
/*import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import lombok.Value;*/
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.data.mongodb.core.MongoClientFactoryBean;*/
import studio.zealous.smartportfolio.config.properties.AngelOneProperties;

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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter TOTP code: ");
        Integer totp = scanner.nextInt();
        User user = smartConnect.generateSession(angelOneProperties().getClientcode(), angelOneProperties().getClientpassword(), String.valueOf(totp));
        smartConnect.setAccessToken(user.getAccessToken());
        smartConnect.setUserId(user.getUserId());

        return smartConnect;
    }
/*    @Bean
    public MongoClientFactoryBean mongo(@Value("${custom.uri}") String uri) {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        ConnectionString conn = new ConnectionString(uri);
        mongo.setConnectionString(conn);
        return mongo;
    }*/
}
