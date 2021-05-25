package pl.umg.owmiaas.Models.City;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sys {
    private int type;
    private int id;
    private String message;
    private String country;
    private long sunrise;
    private long sunset;
}
