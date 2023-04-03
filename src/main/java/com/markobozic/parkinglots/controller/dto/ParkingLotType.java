package com.markobozic.parkinglots.controller.dto;

import java.util.Arrays;

public enum ParkingLotType {
    PARKING("Parking"),
    HOME("Home");

    private final String enumName;

    ParkingLotType(String enumName) {
        this.enumName = enumName;
    }

    public String getEnumName() {
        return enumName;
    }

    public static ParkingLotType fromEnum(String enumType) {
        return Arrays.stream(values())
                .filter(status -> status.toString().equals(enumType))
                .findFirst()
                .orElse(null);
    }

    public static ParkingLotType fromEnumName(String enumName) {
        return Arrays.stream(values())
                .filter(status -> status.enumName.equals(enumName))
                .findFirst()
                .orElse(null);
    }
}
