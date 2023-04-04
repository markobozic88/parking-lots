package com.markobozic.parkinglots.service;

import com.markobozic.parkinglots.controller.dto.ParkingLotDto;
import com.markobozic.parkinglots.mappers.MapperUtils;
import com.markobozic.parkinglots.repository.ParkingLotsRepository;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import static com.markobozic.parkinglots.utils.Validation.validateLatitude;
import static com.markobozic.parkinglots.utils.Validation.validateLongitude;
import static com.markobozic.parkinglots.utils.Validation.validateRadius;

public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotsRepository parkingLotsRepository;

    public ParkingLotServiceImpl(final ParkingLotsRepository parkingLotsRepository) {
        this.parkingLotsRepository = parkingLotsRepository;
    }

    @Override
    public ParkingLotDto findClosestParking(String latitude, String longitude) {
        final double lat = validateLatitude(latitude);
        final double lon = validateLongitude(longitude);

        Optional<ParkingLotEntity> lotEntity = parkingLotsRepository.getClosestParking(lat, lon);
        return lotEntity.map(MapperUtils::fromEntityToDto).orElse(null);
    }

    @Override
    public Integer getParkingScore(String latitude, String longitude, String radius) {
        if (StringUtils.isBlank(radius)) {
            // by default 1.0
            radius = "1.0";
        }
        final double lat = validateLatitude(latitude);
        final double lon = validateLongitude(longitude);
        final double rad = validateRadius(radius);
        return parkingLotsRepository.getParkingScore(lat, lon, rad);
    }
}
