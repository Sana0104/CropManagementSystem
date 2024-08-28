package com.crop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);

	
        PaymentServiceApplication service = new PaymentServiceApplication();
        String result = service.concatenateStrings();
        System.out.println(result);
    }

    public String concatenateStrings() {
        // Inefficient string concatenation in a loop
        String result = "";
        for (int i = 0; i < 1000; i++) {
            result += "Iteration " + i + " ";
        }
        return result;
    }

    public void storePassword() {
        // Hardcoded password (Security flaw)
        String password = "HardCodedPassword123!";
        System.out.println("Password stored: " + password);
        // Password storage logic here
    }

}
