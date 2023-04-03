package com.markobozic.parkinglots.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.markobozic.parkinglots.controller.dto.ParkingLotType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "parking_lots")
public class ParkingLotEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "year", nullable = false)
    private int year;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ParkingLotType type;
}
