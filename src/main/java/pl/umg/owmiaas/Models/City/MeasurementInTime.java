package pl.umg.owmiaas.Models.City;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeasurementInTime {
    @NonNull
    private Date date;
    private Measurement measurement;
}
