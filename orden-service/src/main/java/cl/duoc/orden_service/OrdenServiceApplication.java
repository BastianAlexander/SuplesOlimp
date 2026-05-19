package cl.duoc.orden_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrdenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdenServiceApplication.class, args);
	}
}