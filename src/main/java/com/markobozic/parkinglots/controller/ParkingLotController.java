package com.markobozic.parkinglots.controller;

import com.markobozic.parkinglots.controller.dto.ParkingLotDto;
import com.markobozic.parkinglots.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    public ParkingLotController(@Qualifier("parkingLotService") ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get-closest-parking")
    public ParkingLotDto getClosestParking(@RequestParam("latitude") final String latitude,
                                           @RequestParam("longitude") final String longitude) {
        return parkingLotService.findClosestParking(latitude, longitude);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/parking-score")
    public Integer getParkingScore(@RequestParam("latitude") final String latitude,
                                   @RequestParam("longitude") final String longitude,
                                   @RequestParam(value = "radius", required = false) final String radius) {
        return parkingLotService.getParkingScore(latitude, longitude, radius);
    }
}
