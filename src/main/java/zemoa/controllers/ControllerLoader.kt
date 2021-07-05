package zemoa.controllers

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.util.Callback
import org.springframework.context.ConfigurableApplicationContext

object ControllerLoader {
    fun load(fxml: String, applicationContext: ConfigurableApplicationContext): Parent {
        // Localisation du fichier FXML.
        val url = javaClass.getResource(fxml)
        // Création du loader.
        val fxmlLoader = FXMLLoader(url)
        fxmlLoader.controllerFactory = Callback { clazz -> applicationContext.getBean(clazz) }
        return fxmlLoader.load()
    }
}
