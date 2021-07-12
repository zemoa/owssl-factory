package zemoa.controllers

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.util.Callback
import org.springframework.context.ConfigurableApplicationContext

object ControllerLoader {
    var mapScreen: MutableMap<String, Parent> = HashMap()

    fun preloadScreens(applicationContext: ConfigurableApplicationContext) {
        load(CreateRootCaController.FXML, applicationContext)
    }
    fun load(fxml: String, applicationContext: ConfigurableApplicationContext): Parent {
        if(mapScreen.containsKey(fxml)) {
            return mapScreen[fxml]!!
        }
        // Localisation du fichier FXML.
        val url = javaClass.getResource(fxml)
        // Création du loader.
        val fxmlLoader = FXMLLoader(url)
        fxmlLoader.controllerFactory = Callback { clazz -> applicationContext.getBean(clazz) }
        val parent: Parent = fxmlLoader.load()
        mapScreen[fxml] = parent
        return parent
    }
}
