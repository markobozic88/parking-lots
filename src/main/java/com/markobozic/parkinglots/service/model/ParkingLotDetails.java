package com.markobozic.parkinglots.service.model;

import lombok.Data;

@Data
public class ParkingLotDetails {
    private double latitude;
    private double longitude;
    private String name;
    private int year;
    private String type;

    public ParkingLotDetails(String[] args) {
        this.latitude = Double.parseDouble(args[0]);
        this.longitude = Double.parseDouble(args[1]);
        this.name = args[2];
        this.year = Integer.parseInt(args[3]);
        this.type = args[4];
    }
}
