package zemoa

import javafx.application.Application
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration
import zemoa.OwsslFactoryApp

@SpringBootApplication
@EnableAutoConfiguration(
    exclude=[
        SqlInitializationAutoConfiguration::class,
        TaskSchedulingAutoConfiguration::class,
        TaskExecutionAutoConfiguration::class,
        DataSourceTransactionManagerAutoConfiguration::class,
    ])
class OwsslFactoryLauncher

fun main(args: Array<String>) {
    Application.launch(OwsslFactoryApp::class.java, *args)
}
