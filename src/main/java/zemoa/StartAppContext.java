package zemoa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartAppContext implements ApplicationListener<AppStartingEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartAppContext.class);
    @Override
    public void onApplicationEvent(AppStartingEvent appStartingEvent) {
        LOGGER.warn("App Starting");
    }
}
