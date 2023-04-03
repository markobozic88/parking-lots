package com.markobozic.parkinglots.repository;

import com.markobozic.parkinglots.repository.custom.UUIDRow;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Repository
public interface LoaderRepository extends JpaRepository<ParkingLotEntity, UUID> {

    default List<UUID> findOne(final NamedParameterJdbcOperations jdbcTemplate) {
        StringBuilder query = new StringBuilder().append("select p.id from parking_lots p limit 1");

        try {
            return jdbcTemplate.query(query.toString(), new MapSqlParameterSource(), new UUIDRow());
        } catch (DataAccessException e) {
            throw new IllegalStateException(format("findOne: Call to DB failed! Error: %s", e.getMessage()));
        }

    }
}
