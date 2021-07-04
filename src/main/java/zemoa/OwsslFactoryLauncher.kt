package zemoa

import javafx.application.Application
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import zemoa.OwsslFactoryApp

@SpringBootApplication
class OwsslFactoryLauncher

fun main(args: Array<String>) {
    Application.launch(OwsslFactoryApp::class.java, *args)
}
