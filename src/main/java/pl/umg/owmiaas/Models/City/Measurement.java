package pl.umg.owmiaas.Models.City;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Measurement {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private double visibility;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Snow snow;
    private double dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}
