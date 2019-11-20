package io.spring.boot.microservices.eurekaservicediscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceDiscoveryServerApplication.class, args);
	}

}
