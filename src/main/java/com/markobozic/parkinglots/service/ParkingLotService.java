package com.markobozic.parkinglots.service;

import com.markobozic.parkinglots.controller.dto.ParkingLotDto;

public interface ParkingLotService {
    ParkingLotDto findClosestParking(final String latitude, final String longitude);
    int getParkingScore();
}
