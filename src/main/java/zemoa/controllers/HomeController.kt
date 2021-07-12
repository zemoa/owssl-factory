package zemoa.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import org.slf4j.LoggerFactory
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.stereotype.Component
import zemoa.states.navigation.ChangeScreenEvent

@Component
class HomeController(applicationContext: AbstractApplicationContext): AbstractController(applicationContext) {
    companion object {
        val FXML = "/home.fxml"
        private val LOGGER = LoggerFactory.getLogger(HomeController::class.java)
    }
    @FXML
    fun onCreateRootCertificateClicked(event: ActionEvent) {
        LOGGER.debug("onCreateRootCertificateClicked")
        applicationContext.publishEvent(ChangeScreenEvent(Screen.CREATE_ROOT_CERT))
    }

    @FXML
    fun onCreateCertificateClicked(event: ActionEvent) {
        LOGGER.debug("onCreateCertificateClicked")
        applicationContext.publishEvent(ChangeScreenEvent(Screen.CREATE_CERT))
    }
}
