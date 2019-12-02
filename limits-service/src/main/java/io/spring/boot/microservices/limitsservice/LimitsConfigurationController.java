package io.spring.boot.microservices.limitsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackGetConfiguration")
    public LimitsConfiguration  getConfiguration() {
        throw new RuntimeException("Not available");
    }

    public LimitsConfiguration fallbackGetConfiguration() {
        return new LimitsConfiguration(9, 999);
    }
}
