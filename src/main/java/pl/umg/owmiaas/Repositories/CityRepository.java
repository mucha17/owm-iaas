package pl.umg.owmiaas.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.umg.owmiaas.Models.City.City;

public interface CityRepository extends MongoRepository<City, String> {
    City findCityById(String id);
}
