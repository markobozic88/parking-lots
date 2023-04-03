package com.markobozic.parkinglots.repository;

import com.markobozic.parkinglots.mappers.ParkingLotsRowMapper;
import com.markobozic.parkinglots.mappers.UUIDMapper;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;
import com.markobozic.parkinglots.service.LoaderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLotEntity, UUID> {



    default List<UUID> findOne(final NamedParameterJdbcOperations jdbcTemplate) {

        StringBuilder query = new StringBuilder().append("select p.id from parking_lots p limit 1");

        try {
            return jdbcTemplate.query(query.toString(), new MapSqlParameterSource(), new UUIDMapper());
        } catch (DataAccessException e) {
            throw new IllegalStateException(e);
        }

    }
}
