package ru.nbakaev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.nbakaev.interfaceimplement.annotation.EnableMicroserviceCommunicator;

@SpringBootApplication
@ComponentScan(value = "ru.nbakaev")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableMicroserviceCommunicator
@EnableJpaRepositories
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
}
