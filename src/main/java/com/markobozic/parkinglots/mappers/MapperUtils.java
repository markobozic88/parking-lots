package com.markobozic.parkinglots.mappers;

import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;
import com.markobozic.parkinglots.service.model.ParkingLotDetails;

public class MapperUtils {

    private MapperUtils() {
    }

    public static ParkingLotEntity fromModelToEntity(ParkingLotDetails model) {
        ParkingLotEntity entity = new ParkingLotEntity();
        entity.setLatitude(model.getLatitude());
        entity.setLongitude(model.getLongitude());
        entity.setName(model.getName());
        entity.setYear(model.getYear());
        entity.setType(model.getType());

        return entity;
    }
}
