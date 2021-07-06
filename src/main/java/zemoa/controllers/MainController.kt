package zemoa.controllers

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.layout.AnchorPane
import org.slf4j.LoggerFactory
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.stereotype.Component
import zemoa.states.navigation.NavigationState
import java.util.concurrent.TimeUnit


@Component
class MainController(private var navigationState: NavigationState, applicationContext: AbstractApplicationContext): AbstractController(applicationContext) {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(MainController::class.java)
    }
    @FXML private lateinit var mainContainer: AnchorPane

    @FXML
    fun initialize() {
        navigationState.currentScreen.debounce(50, TimeUnit.MILLISECONDS).subscribe {
                screen ->  changeScreen(screen)}
    }

    private fun changeScreen(screen: Screen) {
        LOGGER.debug("Change screen to $screen")
        Platform.runLater {
            val child = when (screen) {
                Screen.HOME -> ControllerLoader.load("/home.fxml", applicationContext)
                Screen.CREATE_ROOT_CERT -> ControllerLoader.load("/create_root_ca.fxml", applicationContext)
                else -> ControllerLoader.load("/home.fxml", applicationContext)
            }
            mainContainer.children.clear()
            mainContainer.children.add(child)
            AnchorPane.setTopAnchor(child, 0.0)
            AnchorPane.setBottomAnchor(child, 0.0)
            AnchorPane.setLeftAnchor(child, 0.0)
            AnchorPane.setRightAnchor(child, 0.0)
        }
    }
}
