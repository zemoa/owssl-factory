package zemoa.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import org.slf4j.LoggerFactory
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.stereotype.Component
import zemoa.AppStartingEvent
import zemoa.states.navigation.NavigationEvent

@Component
class HomeController(applicationContext: AbstractApplicationContext): AbstractController(applicationContext) {
    companion object {
        val LOGGER = LoggerFactory.getLogger(HomeController::class.java)
    }
    @FXML
    fun onCreateRootCertificateClicked(event: ActionEvent) {
        LOGGER.debug("onCreateRootCertificateClicked")
        applicationContext.publishEvent(NavigationEvent(Screen.CREATE_ROOT_CERT))
    }

    @FXML
    fun onCreateCertificateClicked(event: ActionEvent) {
        LOGGER.debug("onCreateCertificateClicked")
        applicationContext.publishEvent(NavigationEvent(Screen.CREATE_CERT))
    }
}
