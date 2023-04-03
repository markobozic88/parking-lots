package com.markobozic.parkinglots.repository.custom;

import com.markobozic.parkinglots.controller.dto.ParkingLotType;
import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ParkingLotsRow implements RowMapper<ParkingLotEntity> {

    @Override
    public ParkingLotEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ParkingLotEntity entity = new ParkingLotEntity();
        entity.setId(UUID.fromString(rs.getString("id")));
        entity.setLatitude(Double.parseDouble(rs.getString("latitude")));
        entity.setLongitude(Double.parseDouble(rs.getString("longitude")));
        entity.setName(rs.getString("name"));
        entity.setYear(Integer.parseInt(rs.getString("year")));
        entity.setType(ParkingLotType.fromEnum(rs.getString("type")));
        entity.setDistance(Double.parseDouble(rs.getString("distance")));

        return entity;
    }
}
