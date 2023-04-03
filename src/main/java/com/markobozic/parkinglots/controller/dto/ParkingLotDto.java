package com.markobozic.parkinglots.controller.dto;

import lombok.Data;

@Data
public class ParkingLotDto {

    private LocationDto locationDto;

    private String name;

    private int year;

    private ParkingLotType type;

}
