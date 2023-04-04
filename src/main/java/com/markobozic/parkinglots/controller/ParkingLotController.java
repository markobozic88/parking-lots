package com.markobozic.parkinglots.controller;

import com.markobozic.parkinglots.controller.dto.ParkingLotDto;
import com.markobozic.parkinglots.service.ParkingLotService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "API to get closest parking lots with given latitude and longitude.")
    public ParkingLotDto getClosestParking(
            @ApiParam(name = "latitude", value = "Given latitude.")
            @RequestParam("latitude") final String latitude,
            @ApiParam(name = "longitude", value = "Given longitude.")
            @RequestParam("longitude") final String longitude) {
        return parkingLotService.findClosestParking(latitude, longitude);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/parking-score")
    @ApiOperation(value = "API to get number of parking lots with given latitude and longitude." +
            "Radius is not required parameter and by default is 1.0")
    public Integer getParkingScore(
            @ApiParam(name = "latitude", value = "Given latitude.")
            @RequestParam("latitude") final String latitude,
            @ApiParam(name = "longitude", value = "Given longitude.")
            @RequestParam("longitude") final String longitude,
            @ApiParam(name = "radius", value = "Given radius.")
            @RequestParam(value = "radius", required = false) final String radius) {
        return parkingLotService.getParkingScore(latitude, longitude, radius);
    }
}
