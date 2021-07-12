package zemoa

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import javafx.util.Callback
import org.slf4j.LoggerFactory
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext
import zemoa.OwsslFactoryApp
import zemoa.controllers.ControllerLoader
import zemoa.controllers.MainController
import java.io.IOException

/**
 * JavaFX App
 */
class OwsslFactoryApp : Application() {
    private lateinit var applicationContext: ConfigurableApplicationContext
    override fun start(stage: Stage) {
        applicationContext.publishEvent(AppStartingEvent(stage))
        try {
            ControllerLoader.preloadScreens(applicationContext)
            val root = ControllerLoader.load(MainController.FXML, applicationContext)
            val scene = Scene(root, 640.0, 480.0)
            stage.scene = scene
        } catch (ex: IOException) {
            LOGGER.error(ex.message, ex)
        }
        stage.title = "Test FXML"
        stage.show()
    }

    override fun init() {
        applicationContext = SpringApplicationBuilder(OwsslFactoryLauncher::class.java).run()
    }

    override fun stop() {
        applicationContext.close()
        Platform.exit()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OwsslFactoryApp::class.java)
    }
}
