package com.markobozic.parkinglots.config;

import com.markobozic.parkinglots.repository.ParkingLotsRepository;
import com.markobozic.parkinglots.service.ParkingLotService;
import com.markobozic.parkinglots.service.ParkingLotServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean("parkingLotService")
    public ParkingLotService parkingLotService(final ParkingLotsRepository parkingLotsRepository) {
        return new ParkingLotServiceImpl(parkingLotsRepository);
    }

}
