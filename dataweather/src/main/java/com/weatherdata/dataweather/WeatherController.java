package com.weatherdata.dataweather;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class WeatherController {
    private final WeatherService weatherService;

    

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{pincode}/{date}")
    public String getWeatherInfo(@PathVariable String pincode, @PathVariable String date) {
        return weatherService.getWeatherInfo(pincode, date);
    }

}
