package com.example.simple_resful.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/* http://www.baeldung.com/spring-boot-actuators */
// custom health indicator
@Component
public class HealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        int errorCode = check();    // perform some specific health check
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Error code: ", errorCode).build();
        }
        return Health.up().build();
    }

    public int check() {
        return 0;
    }
}
