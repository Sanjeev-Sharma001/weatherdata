package com.weatherdata.dataweather;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.weatherdata.dataweather")
public class DataweatherApplication {

	
    public static void main(String[] args) {
        SpringApplication.run(DataweatherApplication.class, args);
    }
}
