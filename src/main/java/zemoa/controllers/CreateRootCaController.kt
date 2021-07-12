package zemoa.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import org.slf4j.LoggerFactory
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.stereotype.Component
import zemoa.states.rootcertcreate.StartCreationCertEvent
import zemoa.states.navigation.ChangeScreenEvent

@Component
class CreateRootCaController(applicationContext: AbstractApplicationContext): AbstractController(applicationContext) {
    companion object {
        val FXML = "/create_root_ca.fxml"
        private val LOGGER = LoggerFactory.getLogger(HomeController::class.java)
    }

    @FXML lateinit var inputCommonName: TextField
    @FXML lateinit var inputFileName: TextField
    @FXML lateinit var inputDuration: TextField
    @FXML lateinit var inputSecret: TextField
    @FXML lateinit var inputState: TextField
    @FXML lateinit var inputLocality: TextField
    @FXML lateinit var inputOrganization: TextField
    @FXML lateinit var inputOrganizationUnit: TextField
    @FXML lateinit var inputEmail: TextField

    @FXML fun initialize() {
        inputDuration.textFormatter = TextFormatter<String> { change ->
            if (change.controlNewText.matches(Regex("\\D+"))) {
                change.text = change.text.replace(Regex("\\D"), "")
            }
            return@TextFormatter change
        }
    }

    @FXML fun onValidate(event: ActionEvent) {
        applicationContext.publishEvent(StartCreationCertEvent(
            StartCreationCertEvent.Payload(
                commonName = inputCommonName.text,
                name = inputFileName.text,
                duration = inputDuration.text.toLong(),
                secret = inputSecret.text,
                locality = if(inputLocality.text.isBlank()) null else inputLocality.text,
                email = if(inputEmail.text.isBlank()) null else inputEmail.text,
                organization = if(inputOrganization.text.isBlank()) null else inputOrganization.text,
                organizationUnit = if(inputOrganizationUnit.text.isBlank()) null else inputOrganizationUnit.text,
                state = if(inputState.text.isBlank()) null else inputState.text,
                countryName = null
            )
        ))
    }

    @FXML fun onCancel(event: ActionEvent) {
        applicationContext.publishEvent(ChangeScreenEvent(Screen.HOME))
    }
}
