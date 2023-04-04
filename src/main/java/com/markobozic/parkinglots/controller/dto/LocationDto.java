package com.markobozic.parkinglots.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Location latitude and longitude.")
public class LocationDto {

    @ApiModelProperty("Location latitude.")
    private double latitude;

    @ApiModelProperty("Location longitude.")
    private double longitude;

}
