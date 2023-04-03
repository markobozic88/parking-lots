package com.markobozic.parkinglots.repository;

import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;

import java.util.Optional;

public interface ParkingLotsRepository {
    Optional<ParkingLotEntity> getClosestParking(final double latitude, final double longitude);
}
