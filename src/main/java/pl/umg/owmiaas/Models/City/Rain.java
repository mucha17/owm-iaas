package pl.umg.owmiaas.Models.City;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rain {
    @JsonAlias({"1h"})
    private double _1h;
    @JsonAlias({"3h"})
    private double _3h;
}
