package zemoa.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.stereotype.Component
import zemoa.states.certcreate.StartCreationCertEvent
import java.util.function.UnaryOperator

@Component
class CreateRootCaController(applicationContext: AbstractApplicationContext): AbstractController(applicationContext) {
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
}
