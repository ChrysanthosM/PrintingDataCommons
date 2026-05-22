package org.masouras.app.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@Slf4j
public class LoggingConfig {
    @PostConstruct
    public void init() {
        try {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            context.reset();

            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            configurator.doConfigure(Objects.requireNonNull(LoggingConfig.class.getResource("/logback-common.xml")));
        } catch (Exception e) {
            log.error("Failed to load shared Logback config: {}", e.getMessage(), e);
        }
    }
}
