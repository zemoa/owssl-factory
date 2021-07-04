package zemoa

import javafx.stage.Stage
import org.springframework.context.ApplicationEvent

class AppStartingEvent(stage: Stage) : ApplicationEvent(stage)
