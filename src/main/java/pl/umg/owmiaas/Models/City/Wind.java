package pl.umg.owmiaas.Models.City;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wind {
    private double speed;
    private double deg;
    private double gust;
}
