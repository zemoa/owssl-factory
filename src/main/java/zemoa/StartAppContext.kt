package zemoa

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Component
import zemoa.StartAppContext

@EnableAsync
@Component
class StartAppContext : ApplicationListener<AppStartingEvent> {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(StartAppContext::class.java)
    }


    override fun onApplicationEvent(event: AppStartingEvent) {
        LOGGER.info("App Starting using java version ${SystemInfo.javaVersion()} and javafx version ${SystemInfo.javafxVersion()}")
    }
}
