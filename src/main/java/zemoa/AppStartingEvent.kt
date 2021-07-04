package zemoa;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class AppStartingEvent extends ApplicationEvent {
    public AppStartingEvent(Stage stage) {
        super(stage);
    }
}
