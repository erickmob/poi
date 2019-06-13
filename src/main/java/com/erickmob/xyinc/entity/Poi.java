package com.erickmob.xyinc.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.awt.*;

@Entity
@Table(name = "xy_poi")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Poi extends GenericEntity {

    @NotNull(message = "X cannot be null")
    @Min(value = 0, message = "value should be a positive int")
    private int x;

    @NotNull(message = "Y cannot be null")
    @Min(value = 0, message = "value should be a positive int")
    private int y;

    public Point getPoiAsPoint() {
        return new Point(x, y);
    }
}
