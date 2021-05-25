package pl.umg.owmiaas.Models.City;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Main {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double humidity;
    private double sea_level;
    private double grnd_level;
}
