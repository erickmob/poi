package com.erickmob.xyinc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoiDTO {
    @NotNull(message = "X cannot be null")
    @Min(value = 0, message = "value should be a positive int")
    private int x;

    @NotNull(message = "Y cannot be null")
    @Min(value = 0, message = "value should be a positive int")
    private int y;
}
