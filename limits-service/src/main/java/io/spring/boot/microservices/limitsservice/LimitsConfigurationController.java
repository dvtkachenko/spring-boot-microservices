package io.spring.boot.microservices.limitsservice;

import io.spring.boot.microservices.limitsservice.bean.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitsConfiguration getLimitsFromConfigurations() {
        return new LimitsConfiguration(configuration.getMin(), configuration.getMax());
    }
}
