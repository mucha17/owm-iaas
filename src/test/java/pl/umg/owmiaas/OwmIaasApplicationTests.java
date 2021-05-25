package pl.umg.owmiaas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.umg.owmiaas.Models.City.*;
import pl.umg.owmiaas.Repositories.CityRepository;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class OwmIaasApplicationTests {

    @Autowired
    public CityRepository cityRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void createCity() {
        City city = new City("Gdańsk",  "http://api.openweathermap.org/data/2.5/weather?lat=54.07&lon=17.58&appid=4dd978b60c8fc8292aaaf4226c6bb9ff");
        city.setMeasurements(new ArrayList<>());
        this.cityRepository.save(city);
    }

    @Test
    public void createAllCities() {
        List<City> cities = Arrays.asList(
                new City("Gdańsk", "http://api.openweathermap.org/data/2.5/weather?lat=54.20&lon=18.38&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Gdynia", "http://api.openweathermap.org/data/2.5/weather?lat=54.31&lon=18.32&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Słupsk", "http://api.openweathermap.org/data/2.5/weather?lat=54.27&lon=17.01&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Tczew", "http://api.openweathermap.org/data/2.5/weather?lat=54.05&lon=18.46&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Rumia", "http://api.openweathermap.org/data/2.5/weather?lat=54.34&lon=18.23&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Wejherowo", "http://api.openweathermap.org/data/2.5/weather?lat=54.36&lon=18.14&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Starogard Gdański", "http://api.openweathermap.org/data/2.5/weather?lat=53.57&lon=18.31&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Chojnice", "http://api.openweathermap.org/data/2.5/weather?lat=53.41&lon=17.33&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Kwidzyn", "http://api.openweathermap.org/data/2.5/weather?lat=53.44&lon=18.55&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Malbork", "http://api.openweathermap.org/data/2.5/weather?lat=54.02&lon=19.01&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Sopot", "http://api.openweathermap.org/data/2.5/weather?lat=54.26&lon=18.33&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Lębork", "http://api.openweathermap.org/data/2.5/weather?lat=54.32&lon=17.44&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Pruszcz Gdański", "http://api.openweathermap.org/data/2.5/weather?lat=54.15&lon=18.38&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Reda", "http://api.openweathermap.org/data/2.5/weather?lat=54.36&lon=18.20&appid=4dd978b60c8fc8292aaaf4226c6bb9ff"),
                new City("Kościerzyna", "http://api.openweathermap.org/data/2.5/weather?lat=54.07&lon=17.58&appid=4dd978b60c8fc8292aaaf4226c6bb9ff")
        );
        this.cityRepository.saveAll(cities);
    }

    @Test
    public void takeCityMeasurement() throws IOException {
        String id = "60ab7e40d273c46567909a23";
        City city = this.cityRepository.findCityById(id);

        URL url = new URL(city.getApiCallLink());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            // pobranie danych pojedynczego miasta
            StringBuilder response = new StringBuilder();
            BufferedInputStream stream = new BufferedInputStream(url.openStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while (reader.ready()) {
                response.append(reader.readLine());
            }
            System.out.println("\nJSON data in string format");
            System.out.println(response);
            reader.close();
            ObjectMapper objectMapper = new ObjectMapper();
            Measurement parser = objectMapper.readValue(response.toString(), Measurement.class);
            MeasurementInTime measurement = new MeasurementInTime(new Date(), parser);
            System.out.println(measurement.getDate());

            city.getMeasurements().add(measurement);
            this.cityRepository.save(city);
        }
    }
}
