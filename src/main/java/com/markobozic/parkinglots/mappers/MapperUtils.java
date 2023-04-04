package com.markobozic.parkinglots.mappers;

import com.markobozic.parkinglots.controller.dto.LocationDto;
import com.markobozic.parkinglots.controller.dto.ParkingLotDto;
import com.markobozic.parkinglots.controller.dto.ParkingLotType;
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
        entity.setType(ParkingLotType.fromEnumName(model.getType()));

        return entity;
    }

    public static ParkingLotDto fromEntityToDto(ParkingLotEntity entity) {
        ParkingLotDto dto = new ParkingLotDto();
        dto.setLocation(new LocationDto());
        dto.getLocation().setLatitude(entity.getLatitude());
        dto.getLocation().setLongitude(entity.getLongitude());
        dto.setName(entity.getName());
        dto.setYear(entity.getYear());
        dto.setType(entity.getType() != null ? entity.getType().getEnumName() : null);
        dto.setDistance(entity.getDistance());

        return dto;
    }
}
