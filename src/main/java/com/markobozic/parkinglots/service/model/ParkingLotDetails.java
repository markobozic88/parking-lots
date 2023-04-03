package com.markobozic.parkinglots.service.model;

import com.markobozic.parkinglots.controller.dto.ParkingLotType;
import lombok.Data;

@Data
public class ParkingLotDetails {
    private double latitude;
    private double longitude;
    private String name;
    private int year;
    private ParkingLotType type;

    public ParkingLotDetails(String[] args) {
        this.latitude = Double.parseDouble(args[0]);
        this.longitude = Double.parseDouble(args[1]);
        this.name = args[2];
        this.year = Integer.parseInt(args[3]);
        this.type = ParkingLotType.fromFullName(args[4]);
    }
}
