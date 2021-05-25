package pl.umg.owmiaas.Models.City;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
}
