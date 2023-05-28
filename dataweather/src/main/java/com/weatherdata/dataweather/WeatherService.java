package com.weatherdata.dataweather;




import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.weatherdata.dataweather.WeatherInfoRepository;
import com.weatherdata.dataweather.WeatherInfo;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class WeatherService {
    private final PincodeRepository pincodeRepository;
    private final WeatherInfoRepository weatherInfoRepository;

    public WeatherService(PincodeRepository pincodeRepository, WeatherInfoRepository weatherInfoRepository) {
        this.pincodeRepository = pincodeRepository;
        this.weatherInfoRepository = weatherInfoRepository;
    }

    public String getWeatherInfo(String pincode, String dateString) {
        Pincode pincodeEntity = pincodeRepository.findByPincode(pincode);
        if (pincodeEntity == null) {
            return "Invalid pincode";
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (Exception e) {
            return "Invalid date format";
        }

        WeatherInfo weatherInfoOptional = weatherInfoRepository.findByPincodeAndDate(pincodeEntity, date);
        if (weatherInfoOptional!=null) {
            
            return weatherInfoOptional.getWeatherDetails();
        } else {
            String weatherDetails = retrieveWeatherInfo(pincodeEntity, date);
            WeatherInfo newWeatherInfo = new WeatherInfo();
            newWeatherInfo.setPincode(pincode);
            newWeatherInfo.setDate(date);
            newWeatherInfo.setWeatherDetails(weatherDetails);
            weatherInfoRepository.save(newWeatherInfo);
            return weatherDetails;
        }
    }

    private String retrieveWeatherInfo(Pincode pincode, LocalDate date) {
        String apiKey = "356793840fc62d7280f3456c7bbae7f6";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + pincode.getLatitude()
                + "&lon=" + pincode.getLongitude() + "&appid=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        if (response != null) {
            String weatherDetails = ""; 
            return weatherDetails;
        } else {
            return "Failed to retrieve weather information";
        }
    }
}
