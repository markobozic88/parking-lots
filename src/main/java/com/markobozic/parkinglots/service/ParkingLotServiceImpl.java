package com.markobozic.parkinglots.service;

import com.markobozic.parkinglots.controller.dto.ParkingLotDto;
import com.markobozic.parkinglots.mappers.MapperUtils;
import com.markobozic.parkinglots.repository.ParkingLotsRepository;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;

import java.util.Optional;

import static com.markobozic.parkinglots.utils.Validation.validateLatitude;
import static com.markobozic.parkinglots.utils.Validation.validateLongitude;

public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotsRepository parkingLotsRepository;

    public ParkingLotServiceImpl(final ParkingLotsRepository parkingLotsRepository) {
        this.parkingLotsRepository = parkingLotsRepository;
    }

    @Override
    public ParkingLotDto findClosestParking(final String latitude, final String longitude) {
        final double lat = validateLatitude(latitude);
        final double lon = validateLongitude(longitude);

        Optional<ParkingLotEntity> lotEntity = parkingLotsRepository.getClosestParking(lat, lon);
        return lotEntity.map(MapperUtils::fromEntityToDto).orElse(null);
    }

    @Override
    public Integer getParkingScore(final String latitude, final String longitude) {
        final double lat = validateLatitude(latitude);
        final double lon = validateLongitude(longitude);

        Integer score = parkingLotsRepository.getParkingScore(lat, lon);
        if (score == null) {
            return 0;
        }

        return score;
    }
}
