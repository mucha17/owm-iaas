package pl.umg.owmiaas.Models.City;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Snow {
    @JsonAlias({"1h"})
    private double _1h;
    @JsonAlias({"3h"})
    private double _3h;
}
