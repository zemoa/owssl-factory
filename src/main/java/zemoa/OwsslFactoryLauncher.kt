package zemoa;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OwsslFactoryLauncher {
    public static void main(String[] args) {
        Application.launch(OwsslFactoryApp.class, args);
    }
}
