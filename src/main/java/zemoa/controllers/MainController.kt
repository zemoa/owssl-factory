package zemoa.controllers

import io.github.palexdev.materialfx.controls.MFXProgressSpinner
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import org.slf4j.LoggerFactory
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.stereotype.Component
import zemoa.states.main.MainStateHolder
import zemoa.states.navigation.NavigationStateHolder
import java.util.concurrent.TimeUnit


@Component
class MainController(private var navigationState: NavigationStateHolder,
                     private var mainStateHolder: MainStateHolder,
                     applicationContext: AbstractApplicationContext): AbstractController(applicationContext) {
    companion object {
        val FXML = "/main.fxml"
        private val LOGGER = LoggerFactory.getLogger(MainController::class.java)
    }
    @FXML private lateinit var mainContainer: AnchorPane
    @FXML private lateinit var paneModal: Pane
    @FXML private lateinit var progressBar: MFXProgressSpinner

    @FXML
    fun initialize() {
        navigationState.state.debounce(50, TimeUnit.MILLISECONDS).subscribe {
                navState ->  changeScreen(navState.currentScreen)
        }
        mainStateHolder.state.subscribe { mainState ->
            if(mainState.loading) {
                paneModal.visibleProperty().value = true
                progressBar.visibleProperty().value = true
            } else {
                paneModal.visibleProperty().value = false
                progressBar.visibleProperty().value = false
            }
            return@subscribe
        }
    }

    private fun changeScreen(screen: Screen) {
        LOGGER.debug("Change screen to $screen")
        Platform.runLater {
            val child = when (screen) {
                Screen.HOME -> ControllerLoader.load(HomeController.FXML, applicationContext)
                Screen.CREATE_ROOT_CERT -> ControllerLoader.load(CreateRootCaController.FXML, applicationContext)
                else -> ControllerLoader.load(HomeController.FXML, applicationContext)
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
