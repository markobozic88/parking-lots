package com.markobozic.parkinglots.utils;

import com.markobozic.parkinglots.controller.dto.ParkingLotType;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;

public class DataCreator {

    private DataCreator() {
    }

    public static ParkingLotEntity getParkingLotEntity() {
        ParkingLotEntity lotEntity = new ParkingLotEntity();
        lotEntity.setLatitude(34.1012278);
        lotEntity.setLongitude(-118.3408739);
        lotEntity.setName("testName");
        lotEntity.setYear(2023);
        lotEntity.setType(ParkingLotType.PARKING);
        lotEntity.setDistance(120);

        return lotEntity;
    }
}
