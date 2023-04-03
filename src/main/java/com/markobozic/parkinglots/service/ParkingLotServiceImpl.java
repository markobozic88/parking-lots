package com.markobozic.parkinglots.service;

import com.markobozic.parkinglots.controller.dto.ParkingLotDto;
import com.markobozic.parkinglots.mappers.MapperUtils;
import com.markobozic.parkinglots.repository.ParkingLotsRepository;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;

import java.util.Optional;

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
    public int getParkingScore() {
        return 0;
    }

    private double validateLatitude(final String latitude) {
        try {
            return Double.parseDouble(latitude);
        } catch (NullPointerException npe) {
            throw new NullPointerException("latitude can not be null!");
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("latitude does not contain a parsable double!");
        }
    }

    private double validateLongitude(final String longitude) {
        try {
            return Double.parseDouble(longitude);
        } catch (NullPointerException npe) {
            throw new NullPointerException("longitude can not be null!");
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("longitude does not contain a parsable double!");
        }
    }
}
