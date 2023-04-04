package com.markobozic.parkinglots.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Parking lot details.")
public class ParkingLotDto {

    @ApiModelProperty("Information about latitude and longitude.")
    private LocationDto location;

    @ApiModelProperty("Name of the parking owner.")
    private String name;

    @ApiModelProperty("Construction year.")
    private int year;

    @ApiModelProperty("Type of the parking (Parking/Home).")
    private String type;

    @ApiModelProperty("Distance between this location and given location.")
    private double distance;

}
