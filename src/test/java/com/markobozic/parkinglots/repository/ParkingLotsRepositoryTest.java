package com.markobozic.parkinglots.repository;

import com.markobozic.parkinglots.repository.custom.ParkingLotsRow;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.markobozic.parkinglots.utils.ConstsTest.*;
import static com.markobozic.parkinglots.utils.DataCreator.getParkingLotEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ParkingLotsRepository.class})
class ParkingLotsRepositoryTest {

    private ParkingLotsRepository parkingLotsRepository;

    @MockBean
    private NamedParameterJdbcOperations jdbcTemplate;

    @BeforeEach
    public void setUp() {
        parkingLotsRepository = new ParkingLotsRepositoryImpl(jdbcTemplate);
    }

    @Test
    void getClosestParking_returnOptionalEmpty() {
        when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(ParkingLotsRow.class)))
                .thenReturn(Collections.emptyList());

        Optional<ParkingLotEntity> results = parkingLotsRepository.getClosestParking(Double.parseDouble(LAT), Double.parseDouble(LON));
        assertEquals(Optional.empty(), results);
    }

    @Test
    void getClosestParking_returnResults() {
        when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(ParkingLotsRow.class)))
                .thenReturn(Collections.singletonList(getParkingLotEntity()));

        Optional<ParkingLotEntity> results = parkingLotsRepository.getClosestParking(Double.parseDouble(LAT), Double.parseDouble(LON));
        assertFalse(results.isEmpty());
        assertEquals("testName", results.get().getName());
        assertEquals(34.1012278, results.get().getLatitude());
    }

    @Test
    void getParkingScore_returnZero() {
        NamedParameterJdbcOperations jdbcTemplate = mock(NamedParameterJdbcOperations.class);
        when(jdbcTemplate.queryForObject(anyString(), anyMap(), eq(Integer.class))).thenReturn(null);

        int results = parkingLotsRepository.getParkingScore(Double.parseDouble(LAT), Double.parseDouble(LON), Double.parseDouble(RAD));
        assertEquals(0, results);
    }
}
