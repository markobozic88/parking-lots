package com.markobozic.parkinglots.repository.custom;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UUIDRow implements RowMapper<UUID> {
    @Override
    public UUID mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UUID.fromString(rs.getString("id"));
    }
}
