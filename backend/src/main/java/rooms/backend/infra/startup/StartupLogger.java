package rooms.backend.infra.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class StartupLogger {
    private static final Logger log = LoggerFactory.getLogger(StartupLogger.class);

    @Value("${server.port}")
    private String port;

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        log.info("✅ Service Running on port {}", port);
    }
}
