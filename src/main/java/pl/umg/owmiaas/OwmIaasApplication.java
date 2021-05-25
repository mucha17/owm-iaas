package pl.umg.owmiaas;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.umg.owmiaas.Models.City.City;
import pl.umg.owmiaas.Models.City.Measurement;
import pl.umg.owmiaas.Models.City.MeasurementInTime;
import pl.umg.owmiaas.Repositories.CityRepository;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

@EnableScheduling
@RequiredArgsConstructor
@SpringBootApplication
public class OwmIaasApplication {

	private final CityRepository cityRepository;

	public static void main(String[] args) {
		SpringApplication.run(OwmIaasApplication.class, args);
	}

	@Scheduled(cron = "1 * * * * ?")
	public void scheduledTask() {
		List<City> cities = this.cityRepository.findAll();
		for(City city : cities) {
			try {
				URL url = new URL(city.getApiCallLink());
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				int responseCode = connection.getResponseCode();
				if (responseCode != 200) {
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				} else {
					StringBuilder response = new StringBuilder();
					BufferedInputStream stream = new BufferedInputStream(url.openStream());
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
					while (reader.ready()) {
						response.append(reader.readLine());
					}
					reader.close();
					ObjectMapper objectMapper = new ObjectMapper();
					Measurement parser = objectMapper.readValue(response.toString(), Measurement.class);
					MeasurementInTime measurement = new MeasurementInTime(new Date(), parser);
					city.getMeasurements().add(measurement);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.cityRepository.saveAll(cities);
	}

}
