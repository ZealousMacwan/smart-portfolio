package studio.zealous.smartportfolio;

//import com.angelbroking.smartapi.SmartConnect;
//import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import studio.zealous.smartportfolio.config.properties.AngelOneProperties;

@Component
public class CommandLineStartup implements CommandLineRunner {

    @Autowired
    AngelOneProperties angelOneProperties;

    @Autowired
    GetAllHoldings getAllHoldings;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(angelOneProperties.getClientcode());
        try {
            getAllHoldings.getAllHolding();
        } catch (SmartAPIException e) {
            throw new RuntimeException(e);
        }
    }

}

