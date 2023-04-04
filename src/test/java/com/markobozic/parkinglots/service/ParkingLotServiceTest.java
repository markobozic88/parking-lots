package com.markobozic.parkinglots.service;

import com.markobozic.parkinglots.controller.dto.ParkingLotDto;
import com.markobozic.parkinglots.controller.dto.ParkingLotType;
import com.markobozic.parkinglots.repository.ParkingLotsRepository;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.markobozic.parkinglots.utils.ConstsTest.*;
import static com.markobozic.parkinglots.utils.DataCreator.getParkingLotEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ParkingLotService.class})
class ParkingLotServiceTest {

    private ParkingLotService parkingLotService;

    @MockBean
    private ParkingLotsRepository parkingLotsRepository;

    @BeforeEach
    public void setUp() {
        parkingLotService = new ParkingLotServiceImpl(parkingLotsRepository);
    }

    @Test
    void testFindClosestParking_latitudeNull_NullPointerException() {
        assertThrows(NullPointerException.class,
                () -> parkingLotService.findClosestParking(null, LON));
    }

    @Test
    void testFindClosestParking_longitudeNull_NullPointerException() {
        assertThrows(NullPointerException.class,
                () -> parkingLotService.findClosestParking(LAT, null));
    }

    @Test
    void testFindClosestParking_badFormatLatitude_NumberFormatException() {
        assertThrows(NumberFormatException.class,
                () -> parkingLotService.findClosestParking(LAT_BAD, LON));
    }

    @Test
    void testFindClosestParking_badFormatLongitude_NumberFormatException() {
        assertThrows(NumberFormatException.class,
                () -> parkingLotService.findClosestParking(LAT, LON_BAD));
    }

    @Test
    void testFindClosestParking_OK() {
        ParkingLotEntity entity = getParkingLotEntity();
        when(parkingLotsRepository.getClosestParking(anyDouble(), anyDouble())).thenReturn(Optional.of(entity));

        ParkingLotDto dto = parkingLotService.findClosestParking(LAT, LON);
        assertEquals(dto.getLocationDto().getLatitude(), entity.getLatitude());
        assertEquals(dto.getYear(), entity.getYear());
        assertEquals(ParkingLotType.fromEnumName(dto.getType()), entity.getType());
    }

    @Test
    void testGetParkingScore_returnRandomScore_OK() {
        when(parkingLotsRepository.getParkingScore(anyDouble(), anyDouble(), anyDouble())).thenReturn(250);

        Integer score = parkingLotService.getParkingScore(LAT, LON, RAD);
        assertEquals(250, score);
    }

    @Test
    void testGetParkingScore_radiusNull_OK() {
        when(parkingLotsRepository.getParkingScore(anyDouble(), anyDouble(), anyDouble())).thenReturn(250);

        Integer score = parkingLotService.getParkingScore(LAT, LON, null);
        assertEquals(250, score);
    }

    @Test
    void testGetParkingScore_badFormatRadius_NumberFormatException() {
        assertThrows(NumberFormatException.class,
                () -> parkingLotService.getParkingScore(LAT, LON, RAD_BAD));
    }
}
