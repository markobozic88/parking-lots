package com.markobozic.parkinglots.repository;

import com.markobozic.parkinglots.repository.custom.ParkingLotsRow;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.markobozic.parkinglots.utils.Consts.*;
import static java.lang.String.format;

@Component
public class ParkingLotsRepositoryImpl implements ParkingLotsRepository {

    private final NamedParameterJdbcOperations jdbcTemplate;

    private static final double EARTH_RADIUS = 6371; // in kilometers
    private static final Logger LOG = LoggerFactory.getLogger(ParkingLotsRepositoryImpl.class);

    public ParkingLotsRepositoryImpl(final NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<ParkingLotEntity> getClosestParking(final double latitude, final double longitude) {
        final MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(LATITUDE, latitude)
                .addValue(LONGITUDE, longitude)
                .addValue(ER, EARTH_RADIUS);

        StringBuilder query = new StringBuilder()
                .append("select p.id, p.latitude, p.longitude, p.name, p.year, p.type, ")
                .append("acos(sin(radians(:").append(LATITUDE).append(")) * sin(radians(p.latitude)) + ")
                .append("cos(radians(:").append(LATITUDE).append(")) * cos(radians(p.latitude)) * ")
                .append("cos(radians(longitude) - radians(:").append(LONGITUDE).append("))) * ")
                .append(":").append(ER).append(" as distance ")
                .append("from parking_lots p ")
                .append("order by distance ")
                .append("limit 1");

        LOG.info("getClosestParking::query: {}", query);

        try {
            List<ParkingLotEntity> parkingLot = jdbcTemplate.query(query.toString(), parameters, new ParkingLotsRow());
            if (parkingLot.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(parkingLot.get(0));
            }
        } catch (final DataAccessException e) {
            throw new IllegalStateException(format("getClosestParking: Call to DB failed! Error: %s", e.getMessage()));
        }
    }

    @Override
    public Integer getParkingScore(final double latitude, final double longitude) {
        final double radius = 1.0; // in 1km radius
        final MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(LATITUDE, latitude)
                .addValue(LONGITUDE, longitude)
                .addValue(RADIUS, radius);

        StringBuilder query = new StringBuilder()
                .append("select count(*) from parking_lots p where ")
                .append("(:").append(RADIUS).append(" * 1000 * acos(cos(radians(:").append(LATITUDE).append(")) * ")
                .append("cos(radians(p.latitude)) * cos(radians(p.longitude) - ")
                .append("radians(:").append(LONGITUDE).append(")) + sin(radians(:").append(LATITUDE).append(")) ")
                .append("* sin(radians(p.latitude)))) <= :").append(RADIUS);

        LOG.info("getParkingScore::query: {}", query);

        try {
            return jdbcTemplate.queryForObject(query.toString(), parameters, Integer.class);
        } catch (final DataAccessException e) {
            throw new IllegalStateException(format("getParkingScore: Call to DB failed! Error: %s", e.getMessage()));
        }
    }
}










