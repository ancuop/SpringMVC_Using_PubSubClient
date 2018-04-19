package com.example.simple_resful.monitor;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.info.Info.Builder;
import java.util.HashMap;
import java.util.Map;

@Component
public class Info implements InfoContributor {
    @Override
    public void contribute(Builder builder) {
        Map<String, String> data = new HashMap<>();
        data.put("sanh.build.version", "0.0.0.0.9.9.9.9");
        builder.withDetail("buildInfo", data);
    }
}
