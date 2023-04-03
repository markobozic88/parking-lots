package com.markobozic.parkinglots.service;

import com.markobozic.parkinglots.controller.dto.ParkingLotDto;

public interface ParkingLotService {

    ParkingLotDto findClosestParking(double latitude, double longitude);

    int getParkingScore();
}
