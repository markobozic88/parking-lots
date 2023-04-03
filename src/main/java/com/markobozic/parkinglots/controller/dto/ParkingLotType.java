package com.markobozic.parkinglots.controller.dto;

import java.util.Arrays;

public enum ParkingLotType {
    PARKING("Parking"),
    HOME("Home");

    private final String enumName;

    ParkingLotType(String enumName) {
        this.enumName = enumName;
    }

    public static ParkingLotType fromFullName(String name) {
        return Arrays.stream(values())
                .filter(status -> status.enumName.equals(name))
                .findFirst()
                .orElse(null);
    }
}
