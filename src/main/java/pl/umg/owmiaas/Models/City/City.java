package pl.umg.owmiaas.Models.City;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("cities")
public class City {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String apiCallLink;
    private List<MeasurementInTime> measurements;

    public City(String name, String apiCallLink) {
        this.name = name;
        this.apiCallLink = apiCallLink;
        this.measurements = new ArrayList<>();
    }
}
