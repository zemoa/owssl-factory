package zemoa;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.net.URL;


/**
 * JavaFX App
 */
public class OwsslFactoryApp extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(OwsslFactoryApp.class);
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new AppStartingEvent(stage));

        try {
            // Localisation du fichier FXML.
            final URL url = getClass().getResource("/helloworld.fxml");
            // Création du loader.
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            // Chargement du FXML.
            final AnchorPane root = (AnchorPane) fxmlLoader.load();
            var scene = new Scene(root, 640, 480);
            stage.setScene(scene);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        stage.setTitle("Test FXML");
        stage.show();
    }

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(OwsslFactoryLauncher.class).run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}
